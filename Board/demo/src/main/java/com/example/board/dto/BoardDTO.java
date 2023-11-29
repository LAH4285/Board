package com.example.board.dto;


import com.example.board.entity.Board;
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

    // ** 내용
    private String contents;

    // ** 최초 작성 시간
    private LocalDateTime createTime;
    // ** 수정 시간
    private LocalDateTime updateTime;

    // ** 엔티티
    public Board toEntity() {
        return Board.builder()
                .title(title)
                .contents(contents)
                .createTime(createTime)
                .updateTime(updateTime)
                .build();
    }

    public static BoardDTO toBoardDTO(Board board){
        return new BoardDTO(
                board.getId(),
                board.getTitle(),
                board.getContents(),
                board.getCreateTime(),
                board.getUpdateTime()
          );
    }
}
