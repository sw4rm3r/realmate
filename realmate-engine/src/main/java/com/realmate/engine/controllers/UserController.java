package com.realmate.engine.controllers;

import com.realmate.engine.models.User;
import com.realmate.engine.repository.UserRepository;
import com.realmate.engine.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/realmate/user")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtUtils jwtUtils;

    @GetMapping("/info")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<User> getUserInfo(@RequestHeader(name="Authorization") String token) {
        User currentUser = jwtUtils.getUserFromJwtToken(token.substring(7));
        return ResponseEntity.ok(currentUser);
    }
}
