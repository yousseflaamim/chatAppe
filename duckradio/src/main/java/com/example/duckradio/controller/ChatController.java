package com.example.duckradio.controller;

import com.example.duckradio.modell.Message;
import com.example.duckradio.repositroy.ChannelRepository;
import com.example.duckradio.repositroy.MessageRepository;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("")
public class ChatController {
    private final MessageRepository messageRepository ;
    private  final ChannelRepository channelRepository;

    private final SimpMessageSendingOperations messagingTemplate;

    public ChatController(MessageRepository messageRepository, ChannelRepository channelRepository, SimpMessageSendingOperations messagingTemplate) {
        this.messageRepository = messageRepository;
        this.channelRepository = channelRepository;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/channels/{channelId}/messages")
    @SendTo("/sub/chat/{channelId}")
    public void handleMessage(@Payload String content, @DestinationVariable Long channelId, Principal principal) {
        Message message = new Message();
        message.setContent(content);
        message.setChannel(channelRepository.getOne(channelId));
        messageRepository.save(message);

        messagingTemplate.convertAndSend("/sub/channels/" + channelId + "/messages", message);
    }
  //  @MessageMapping("/message/{channelId}")

  //  public Message handleMessage(@Payload Message message, @DestinationVariable Long channelId) {
       // return message;
   // }

}
