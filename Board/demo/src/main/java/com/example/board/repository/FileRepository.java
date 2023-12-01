package com.example.board.repository;

import com.example.board.entity.Board;
import com.example.board.entity.BoardFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FileRepository extends JpaRepository<BoardFile, Long> {
    List<BoardFile> findByBoardId(Long boardId);
    Optional<BoardFile> findByFileName(BoardFile boardFile);
    List<BoardFile> findByBoard(Board board);

}
