package com.tiktac.toe.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long boardId;

    @Column
    private char[] board = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public void setBoardFieldById(int fieldId, char mark) {
        this.board[fieldId] = mark;
    }

}
