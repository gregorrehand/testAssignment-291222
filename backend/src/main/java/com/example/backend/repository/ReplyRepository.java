package com.example.backend.repository;

import com.example.backend.model.Reply;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReplyRepository
        extends CrudRepository<Reply, UUID> {
}