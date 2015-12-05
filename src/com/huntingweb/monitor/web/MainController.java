package com.huntingweb.monitor.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/")
public class MainController {
	@RequestMapping(method = GET)
	public String monitor() {
		return "monitor/index";
	}

	@RequestMapping(value = "monitor-content", method = GET)
	public String monitorContent() {
		return "monitor/content/content";
	}

	@RequestMapping(value = "monitor-feedback", method = GET)
	public String monitorFeedback() {
		return "monitor/content/feedback";
	}

	@RequestMapping(value = "management", method = GET)
	public String management() {
		return "management/index";
	}

	@RequestMapping(value = { "management/project-function" }, method = GET)
	public String managementProjectFunction() {
		return "management/project/function";
	}

	@RequestMapping(value = { "management/client-function" }, method = GET)
	public String managementClientFunction() {
		return "management/client/function";
	}

	@RequestMapping(value = { "management/project-detail" }, method = GET)
	public String managementProjectDetail() {
		return "management/project/detail";
	}

	@RequestMapping(value = { "management/client-detail" }, method = GET)
	public String managementClientDetail() {
		return "management/client/detail";
	}

	@RequestMapping(value = { "management/project-detail-form" }, method = GET)
	public String managementProjectDetailForm() {
		return "management/project/detail/form";
	}

	@RequestMapping(value = { "management/client-detail-form" }, method = GET)
	public String managementClientDetailForm() {
		return "management/client/detail/form";
	}

	@RequestMapping(value = "management/project-detail-content", method = GET)
	public String managementProjectDetailContent() {
		return "management/project/detail/content";
	}

	@RequestMapping(value = { "management/project-detail-content-data" }, method = GET)
	public String managementProjectDetailContentData() {
		return "management/project/detail/content/data";
	}

	@RequestMapping(value = { "management/project-detail-content-progresses" }, method = GET)
	public String managementProjectDetailContentProgresses() {
		return "management/project/detail/content/progresses";
	}

	@RequestMapping(value = { "management/project-detail-content-messages" }, method = GET)
	public String managementProjectDetailContentMessages() {
		return "management/project/detail/content/messages";
	}

	@RequestMapping(value = { "management/project-detail-content-progressForm" }, method = GET)
	public String managementProjectDetailContentProgressForm() {
		return "management/project/detail/content/progress-form";
	}

	@RequestMapping(value = { "management/project-detail-content-progress" }, method = GET)
	public String managementProjectDetailContentProgress() {
		return "management/project/detail/content/progress";
	}

	@RequestMapping(value = { "management/client-detail-data" }, method = GET)
	public String managementClienttDetailData() {
		return "management/client/detail/data";
	}

	@RequestMapping(value = "login", method = GET)
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String lougout, Model model) {
		if (error != null)
			model.addAttribute("error", "账号和密码不符合，请再次尝试或联系我们");
		return "login";
	}

}