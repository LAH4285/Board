package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void join(UserDTO.JoinDTO requestDTO){
        checkEmail(requestDTO.getEmail());

        //String encodedPassword = passwordEncoder.encode(requestDTO.getPassword());
        //requestDTO.setPassword(encodedPassword);

        userRepository.save(requestDTO.toEntity());
    }
    public void findAll() {
        List<User> all = userRepository.findAll();
        for(User user : all) {

        }
    }

    public void checkEmail(String email){
        Optional<User> users = userRepository.findByEmail(email);
        if(users.isPresent()) {
            //throw new Exception400("이미 존재하는 이메일 입니다. : " + email);
        }
    }

}
