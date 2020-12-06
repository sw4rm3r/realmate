package com.realmate.engine.controllers;

import com.realmate.engine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/realmate/manager")
public class AdministrationController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> getAllQuestions() {
        return ResponseEntity.ok(this.userRepository.findAll());
    }
}
