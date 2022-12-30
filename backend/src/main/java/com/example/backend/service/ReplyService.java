package com.example.backend.service;

import com.example.backend.model.Reply;
import com.example.backend.repository.ReplyRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReplyService {
    @Autowired
    private ReplyRepository replyRepository;

    public List<Reply> getAll() {
        return (List<Reply>) replyRepository.findAll();
    }
    public Reply save(Reply reply) {
        return replyRepository.save(reply);
    }

    @SneakyThrows
    public Reply update(Reply reply, UUID id) {
        Reply existingRecord = replyRepository.findById(id).get();
        existingRecord.setName(reply.getName());
        existingRecord.setSectors(reply.getSectors());
        existingRecord.setAgreeToTerms(reply.isAgreeToTerms());
        return replyRepository.save(existingRecord);
    }
    public void delete(UUID id) {
        replyRepository.deleteById(id);
    }
}
