package com.example.board.controller;

import com.example.board.dto.CommentDTO;
import com.example.board.entity.Comment;
import com.example.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/save")
    public ResponseEntity save(@ModelAttribute CommentDTO commentDTO) {

        System.out.println(commentDTO);
        Comment comment = commentService.save(commentDTO);

        List<CommentDTO> all = commentService.findAll(commentDTO.getBoardId());

        if(comment != null) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("게시글이 없음.", HttpStatus.NOT_FOUND);
        }
    }
}









