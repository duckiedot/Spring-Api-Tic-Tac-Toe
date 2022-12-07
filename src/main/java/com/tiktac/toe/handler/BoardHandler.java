package com.tiktac.toe.handler;

import com.tiktac.toe.domain.Board;
import org.springframework.stereotype.Component;

@Component
public class BoardHandler {
    public Board setBoardField(Board board, int selectedRow, int selectedColumn, char mark) {
        board.setBoardFieldById(selectedRow, selectedColumn, mark);
        return board;
    }
}
