package com.nuracell.simplerestapi.repository;

import com.nuracell.simplerestapi.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
