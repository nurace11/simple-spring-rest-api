package com.nuracell.simplerestapi.service;

import com.nuracell.simplerestapi.entity.Message;
import com.nuracell.simplerestapi.repository.MessageRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    private final Integer MAX_PAGE_LIMIT = 100;


    public List<Message> findLimited(Pageable pageable) {
        if (pageable.getPageNumber() * pageable.getPageSize() >= MAX_PAGE_LIMIT) {
            throw new IllegalArgumentException(
                    "Invalid page request. Page * Size must be less than " + MAX_PAGE_LIMIT
            );
        }
        return messageRepository.findAll(pageable).getContent();
    }

    @Cacheable("messages")
    public Message findById(Long id) {
        return messageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Message with id %d not found".formatted(id)));
    }

    public Message createMessage(Message message) {
        message.setCreatedAt(LocalDateTime.now());
        return messageRepository.save(message);
    }

    @CachePut(value = "messages", key = "#id")
    public Message updateMessage(Long id, Message message) {
        Message messageToUpdate = messageRepository.findById(id).orElseThrow();

        messageToUpdate.setText(message.getText());

        return messageRepository.save(messageToUpdate);
    }

    @CacheEvict("messages")
    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }
}
