package com.example.board.controller;

import com.example.board.dto.BoardDTO;
import com.example.board.dto.CommentDTO;
import com.example.board.entity.Comment;
import com.example.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    @GetMapping("/list/{boardId}")
    public ResponseEntity<List<CommentDTO>> getComments(@PathVariable Long boardId) {
        List<CommentDTO> comments = commentService.findAll(boardId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping(value = {"/paging", "/"})
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model modal){

        Page<CommentDTO> commentDTO = commentService.paging(pageable);

        int blockLimit = 3;
        int startPage = (int)(Math.ceil((double)pageable.getPageNumber() / blockLimit) - 1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < commentDTO.getTotalPages()) ? (startPage + blockLimit - 1) : commentDTO.getTotalPages();

        modal.addAttribute("commentList", commentDTO);
        modal.addAttribute("startPage", startPage);
        modal.addAttribute("endPage",endPage);

        return "detail";
    }


    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        Optional<Comment> commentOptional = commentService.findById(id);

        model.addAttribute("commentDTO", commentOptional);
        return "update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute CommentDTO commentDTO) {
        commentService.update(commentDTO);
        return "redirect:/board/paging/";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        System.out.println(id);
        commentService.delete(id);
        return ResponseEntity.ok("삭제 성공");
    }
}








