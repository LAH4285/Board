package com.example.board.controller;

import com.example.board.core.security.JwtTokenProvider;
import com.example.board.core.utils.ApiUtils;
import com.example.board.dto.UserDTO;
import com.example.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/custom")
public class UserController {

    private final UserService userService;
    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody @Valid UserDTO.JoinDTO requestDTO, Error error) {

        userService.join(requestDTO);

        return ResponseEntity.ok( ApiUtils.success(null) );
    }

    @PostMapping("/check")
    public ResponseEntity<?> check(@RequestBody @Valid UserDTO.JoinDTO requestDTO, Error error) {
        userService.checkEmail(requestDTO.getEmail());
        return ResponseEntity.ok( ApiUtils.success(null) );
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserDTO.JoinDTO requestDTO, Error error) {

        String jwt = userService.login(requestDTO);

        return ResponseEntity.ok().header(JwtTokenProvider.HEADER, jwt)
                .body(ApiUtils.success(null));
    }
}