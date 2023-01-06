package com.example.backend.controller;

import com.example.backend.model.Reply;
import com.example.backend.service.ReplyService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins =  "http://localhost:8090", allowCredentials = "true")
@RequestMapping("/replys")
public class ReplyController {

    @Autowired private ReplyService replyService;

    @GetMapping
    public List<Reply> getReplys()
    {
        return replyService.getAll();
    }

    @GetMapping("/{id}")
    public Reply getReplyById(@PathVariable("id") UUID id)
    {
        return replyService.getById(id);
    }

    @PostMapping()
    public Reply saveReply(
            @Valid @RequestBody Reply reply, HttpServletRequest request)
    {
        Reply savedReply = replyService.save(reply);
        request.getSession().setAttribute("CURRENT_REPLY_ID", savedReply.getId());
        return savedReply;
    }
    
    @PutMapping("/{id}")
    public Reply updateReply(@Valid @RequestBody Reply reply,
                     @PathVariable("id") UUID replyId)
    {
        return replyService.update(reply, replyId);
    }
    @DeleteMapping("/{id}")
    public String deleteReply(@PathVariable("id")
                                       UUID replyId)
    {
        replyService.delete(replyId);
        return "Deleted Successfully";
    }
}