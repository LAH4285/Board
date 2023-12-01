package com.example.board.controller;

import com.example.board.core.security.JwtTokenProvider;
import com.example.board.core.utils.ApiUtils;
import com.example.board.dto.UserDTO;
import com.example.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody @Valid UserDTO.JoinDTO requestDTO, Error error) {

        userService.join(requestDTO);

        return ResponseEntity.ok( ApiUtils.success(null) );
    }

    @GetMapping("/join")
    public String join(Model model) {

        return "join.html"; // "join.html" 파일을 렌더링
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