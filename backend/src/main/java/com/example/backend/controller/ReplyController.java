package com.example.backend.controller;

import com.example.backend.model.Reply;
import com.example.backend.service.ReplyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
public class ReplyController {

    @Autowired private ReplyService replyService;
    
    @GetMapping("/replys")
    public List<Reply> getReplys()
    {
        return replyService.getAll();
    }

    @PostMapping("/replys")
    public Reply saveReply(
            @Valid @RequestBody Reply reply)
    {
        return replyService.save(reply);
    }
    
    @PutMapping("/replys/{id}")
    public Reply updateReply(@Valid @RequestBody Reply reply,
                     @PathVariable("id") UUID replyId)
    {
        return replyService.update(reply, replyId);
    }
    @DeleteMapping("/replys/{id}")
    public String deleteReply(@PathVariable("id")
                                       UUID replyId)
    {
        replyService.delete(replyId);
        return "Deleted Successfully";
    }
}