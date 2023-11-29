package com.example.board.service;

import com.example.board.dto.BoardDTO;
import com.example.board.dto.CommentDTO;
import com.example.board.entity.Board;
import com.example.board.entity.Comment;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    // ** 댓글을 저장하는 서비스기능으로 Transactional을 사용함으로써 읽기전용에서 사용 가능하다
    @Transactional
    public Comment save(CommentDTO commentDTO) {
        Optional<Board> optionalBoard =
                boardRepository.findById(commentDTO.getBoardId());

        if(optionalBoard.isPresent()) {
            Board board = optionalBoard.get();

            Comment entity = commentDTO.toEntity();
            entity.toUpdate(board);
            return commentRepository.save(entity);
        } else {
            return null;
        }
    }

    public List<CommentDTO> findAll(Long boardId) {
        Board boardEntity = boardRepository.findById(boardId).get();
        List<Comment> commentEntityList = commentRepository.findAllByBoardOrderByIdDesc(boardEntity);
        /* EntityList -> DTOList */
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (Comment commentEntity: commentEntityList) {
            CommentDTO commentDTO = CommentDTO.toCommentDTO(commentEntity, boardId);
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }

    public void delete(Long id){
        commentRepository.deleteById(id);
    }

    @Transactional
    public void update(CommentDTO commentDTO) {
        Optional<Comment> commentOptional = commentRepository.findById(commentDTO.getId());

        //if(boardOptional.isPresent()) ... 예외처리 생략
        Comment comment = commentOptional.get();

        comment.updateFromDTO(commentDTO);

        commentRepository.save(comment);
    }
}
