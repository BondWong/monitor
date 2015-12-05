<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="chat-container clearfix" draggable>
	<div class="chat">
		<div class="chat-header clearfix">
			<img
				src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/195612/chat_avatar_01_green.jpg"
				alt="avatar" />

			<div class="chat-about">
				<div class="chat-with">反馈</div>
				<div class="chat-num-messages">我们将尽快回复</div>
			</div>
		</div>
		<div class="chat-history">
			<ul>
			</ul>
		</div>
		<div class="chat-message clearfix">
			<textarea name="message-to-send" id="message-to-send"
				placeholder="Type your message" rows="3" class="box-size"
				ng-model="messageToSend" ng-click="focus()"></textarea>
			<button ng-click="showFileDialog()" class="pull-left">
				<i class="glyphicon glyphicon-export"></i>
			</button>
			<button ng-click="send()">Send</button>
		</div>
	</div>
	<input id="feedbackFile" type="file" class="form-control"
		style="visibility: hidden;" file-model="file" />
</div>