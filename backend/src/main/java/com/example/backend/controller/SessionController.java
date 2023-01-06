package com.example.backend.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins =  "http://localhost:8090", allowCredentials = "true")
public class SessionController {

    @GetMapping("/session")
    public ResponseEntity<UUID> getMessage(HttpSession session) {
        UUID currentReplyId = (UUID) session.getAttribute("CURRENT_REPLY_ID");
        return new ResponseEntity<>(currentReplyId, HttpStatus.OK);
    }

    @DeleteMapping("/session")
    public ResponseEntity endSession(HttpServletRequest request)
    {
        request.getSession().invalidate();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}