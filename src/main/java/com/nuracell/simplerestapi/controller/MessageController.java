package com.nuracell.simplerestapi.controller;

import com.nuracell.simplerestapi.entity.Message;
import com.nuracell.simplerestapi.service.MessageService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping
    public ResponseEntity<List<Message>> getMessages(@PathParam("page") Integer page) {
        return ResponseEntity.ok(messageService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable("id") Long id)  {
        return ResponseEntity.ok(messageService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        return ResponseEntity.ok(messageService.createMessage(message));
    }

    @PutMapping("{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable("id") Long id, @RequestBody Message message) {
        return ResponseEntity.ok(messageService.updateMessage(id, message));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteMessage(@PathVariable("id") Long id) {
        messageService.deleteMessage(id);
        return ResponseEntity.noContent().build();
    }
}
