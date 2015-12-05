package com.huntingweb.monitor.web;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huntingweb.monitor.domain.File;
import com.huntingweb.monitor.transaction.Transaction;

@RestController
public class FileUploadService {
	private final static String fileFolder = "uploadedFiles";
	private final static int bytesPerInteger = 4;

	@Autowired
	private ServletContext servletContext;
	@Autowired
	@Qualifier("ProgFileDelTrans")
	private Transaction progressFileDeletionTransaction;
	@Autowired
	@Qualifier("ProjFileDelTrans")
	private Transaction projectFileDeletionTransaction;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/file", method = POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<File> file(@RequestBody Map<String, Object> params) throws IOException {
		Path rootPath = Paths.get(servletContext.getRealPath("/"));

		String id = (String) params.get("id");
		String name = (String) params.get("name");
		String type = (String) params.get("type");
		String description = (String) params.get("type");
		List<Integer> integers = (List<Integer>) params.get("bytes");
		int size = integers.size();

		Path dirPath = Paths.get(rootPath.toString(), fileFolder, id);
		Path filePath = Paths.get(dirPath.toString(), name);
		if (Files.notExists(rootPath))
			Files.createDirectory(rootPath);
		if (Files.notExists(dirPath))
			Files.createDirectories(dirPath);
		Files.createFile(filePath);

		byte[] bytes = new byte[size];
		ByteBuffer buffer = ByteBuffer.allocate(bytesPerInteger);
		buffer.order(ByteOrder.LITTLE_ENDIAN);
		for (int i = 0; i < size; i++) {
			buffer.putInt(integers.get(i));
			bytes[i] = buffer.get(0);
			buffer.clear();
		}
		Files.write(filePath, bytes);
		File file = new File(id, name, size, type, rootPath.relativize(filePath).toString(), description);
		return ResponseEntity.ok(file);
	}

	@RequestMapping(value = "/file/{type:\\w+}/{ownerId:\\d+}/{fileId:\\d+}", method = DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> file(@PathVariable("type") String type, @PathVariable("ownerId") String ownerId,
			@PathVariable("fileId") String fileId) {
		Transaction transaction = type.equals("progress") ? progressFileDeletionTransaction
				: projectFileDeletionTransaction;
		transaction.execute(null, ownerId, fileId);
		return ResponseEntity.ok().build();
	}
}
