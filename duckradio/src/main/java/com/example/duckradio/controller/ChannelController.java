package com.example.duckradio.controller;

import com.example.duckradio.modell.Channel;
import com.example.duckradio.repositroy.ChannelRepository;
import com.example.duckradio.repositroy.MessageRepository;
import com.example.duckradio.service.ChannelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.WebSocketHandler;

import java.util.List;
@RestController
@RequestMapping("/channel")
public class ChannelController {

    private final ChannelRepository channelRepository;
    private  final ChannelService channelService;
    private final MessageRepository messageRepository;

    public ChannelController(ChannelRepository channelRepository, ChannelService channelService, MessageRepository messageRepository) {
        this.channelRepository = channelRepository;
        this.channelService = channelService;
        this.messageRepository = messageRepository;
    }

  //  @GetMapping("/api/channels")
    @RequestMapping(method = RequestMethod.GET,path = "/getAllChannels")
    public List<Channel> getAllChannels() {
        return channelService.getAllChannels();
    }

    @PostMapping("/createChannel")
    public ResponseEntity<Channel> createChannel(@RequestBody Channel channel) {
        Channel createdChannel = channelService.createChannel(channel);
        return new ResponseEntity<>(createdChannel, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteChannel/{id}")
    public void deleteChannel(@PathVariable Long id) {
        channelService.deleteChannelById(id);
    }

    @RequestMapping("/error")
    public String handleError() {
        return "An error has occurred.";
    }
}
