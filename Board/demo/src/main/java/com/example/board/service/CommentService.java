package com.example.board.service;

import com.example.board.dto.BoardDTO;
import com.example.board.dto.CommentDTO;
import com.example.board.entity.Board;
import com.example.board.entity.Comment;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Page<CommentDTO> paging(Pageable pageable) {
        // ** 페이지 시작 번호 셋팅
        int page = pageable.getPageNumber() - 1;
        // ** 페이지에 포함될 게시물 개수
        int size = 5;
        // ** 전체 게시물을 불러온다.
        Page<Comment> comments = commentRepository.findAll(
                PageRequest.of(page, size));

        return comments.map(comment -> new CommentDTO(
                comment.getId(),
                comment.getWriter(),
                comment.getContents()
                ));
    }

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
    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }
    @Transactional
    public void delete(Long id){
        commentRepository.deleteById(id);
    }
    @Transactional
    public void update(CommentDTO commentDTO) {
        Optional<Comment> commentOptional = commentRepository.findById(commentDTO.getId());

        Comment comment = commentOptional.get();

        comment.updateFromDTO(commentDTO);

        commentRepository.save(comment);
    }
}
