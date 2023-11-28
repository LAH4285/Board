package com.example.board.service;

import com.example.board.core.error.exception.Exception400;
import com.example.board.core.error.exception.Exception401;
import com.example.board.core.error.exception.Exception500;
import com.example.board.core.security.CustomUserDetails;
import com.example.board.core.security.JwtTokenProvider;
import com.example.board.dto.UserDTO;
import com.example.board.entity.User;
import com.example.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Transactional
    public void join(UserDTO.JoinDTO requestDTO) {
        checkEmail(requestDTO.getEmail());

        String encodedPassword = passwordEncoder.encode( requestDTO.getPassword());

        requestDTO.setPassword(encodedPassword);

        try {
            userRepository.save(requestDTO.toEntity());
        }catch (Exception e){
            throw new Exception500(e.getMessage());
        }
    }

    public String login(UserDTO.JoinDTO requestDTO) {
        try{
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                    = new UsernamePasswordAuthenticationToken(requestDTO.getEmail(), requestDTO.getPassword());

            Authentication authentication =  authenticationManager.authenticate(
                    usernamePasswordAuthenticationToken
            );
            CustomUserDetails customUserDetails = (CustomUserDetails)authentication.getPrincipal();

            return JwtTokenProvider.create(customUserDetails.getUser());
        }catch (Exception e){
            throw new Exception401("인증되지 않음.");
        }
    }

    public void findAll() {
        List<User> all = userRepository.findAll();
        for(User user : all){
            user.output();
        }
    }

    public void checkEmail(String email){
        Optional<User> users = userRepository.findByEmail(email);
        if(users.isPresent()) {
            throw new Exception400("이미 존재하는 이메일 입니다. : " + email);
        }
    }
}
