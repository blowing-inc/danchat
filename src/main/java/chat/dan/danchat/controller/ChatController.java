package chat.dan.danchat.controller;

import chat.dan.danchat.model.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.Map;
import java.util.regex.*;


@Controller
public class ChatController {

	private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

	private Map<String, String> availableDanMojis;

	public ChatController(Map<String, String> danMojiList) {
		this.availableDanMojis = danMojiList;
	}

	@MessageMapping("/chat.sendMessage")
	@SendTo("/topic/public")
	public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {

		String content = chatMessage.getContent();
		String filteredMessage = "";
		// Filter messages for DanChat only
		Pattern p = Pattern.compile(":([a-z]\\w+Dan):");
		Matcher m = p.matcher(content);

		while(m.find()) {
			//Handling all filtering server-side. sending up valid image addresses.
			if(availableDanMojis.keySet().contains(m.group(1))) {
				logger.info("\trequested Danmoji: " + m.group(1));
				filteredMessage += ("/danmojis/" + m.group(1) + ".png,");
			}
		}

		if(filteredMessage.isEmpty()) {
			return null;
		} else {
			chatMessage.setContent(filteredMessage);
		}

		logger.info("RECV:  " + chatMessage.getSender() + ": " + chatMessage.getContent());
		return chatMessage;
	}

	@MessageMapping("/chat.addUser")
	@SendTo("/topic/public")
	public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
		headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
		return chatMessage;
	}
}

