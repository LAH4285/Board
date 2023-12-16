package com.example.demo.board;


import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {


    private Long id;

    // ** 제목
    private String title;

    private String contents;

    private String writer;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .contents(contents)
                .writer(writer)
                .createTime(createTime)
                .updateTime(updateTime)
                .build();
    }

    public static BoardDTO toBoardDTO(Board board){
        return new BoardDTO(
                board.getId(),
                board.getTitle(),
                board.getContents(),
                board.getWriter(),
                board.getCreateTime(),
                board.getUpdateTime() );
    }
}
