package com.example.board.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class BoardFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ** 파일 경로
    private String filePath;

    // ** 파일 이름
    private String fileName;

    // ** 파일 포멧
    private String fileType;

    private String uuid;
    // ** 파일 크기
    private Long fileSize;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    public BoardFile(Long id, String filePath, String fileName, String fileType, String uuid, Long fileSize, Board board) {
        this.id = id;
        this.filePath = filePath;
        this.fileName = fileName;
        this.fileType = fileType;
        this.uuid = uuid;
        this.fileSize = fileSize;
        this.board = board;
    }

    public BoardFile toUpdate(Board board) {
        BoardFile boardFile = new BoardFile();
        this.board = board;
        return boardFile;
    }
}













