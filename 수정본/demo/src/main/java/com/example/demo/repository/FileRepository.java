package com.example.demo.repository;

import com.example.demo.entity.Board;
import com.example.demo.entity.BoardFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FileRepository extends JpaRepository<BoardFile, Long> {
    Optional<BoardFile> findByFileName(BoardFile boardFile);
    List<BoardFile> findByBoard(Board board);
}
