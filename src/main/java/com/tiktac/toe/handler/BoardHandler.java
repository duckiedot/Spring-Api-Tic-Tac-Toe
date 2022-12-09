package com.tiktac.toe.handler;

import com.google.gson.JsonObject;
import com.tiktac.toe.domain.Board;
import org.springframework.stereotype.Component;

@Component
public class BoardHandler {
    public Board setBoardField(Board board, int fieldId, char mark) {
        board.setBoardFieldById(
                fieldId,
                mark
        );
        return board;
    }

    public String displayBoard(Board board) {
        JsonObject jsonObject = new JsonObject();
        char[] gameBoard = board.getBoard();

        for (int i = 0; i < gameBoard.length; i += 3) {
            StringBuilder rowOutput = new StringBuilder();
            for (int currentField = i; currentField < i + 3; currentField++) {
                char currentMark = gameBoard[currentField] == 'X' || gameBoard[currentField] == 'O'
                        ? gameBoard[currentField] : '.';
                rowOutput.append(" ").append(currentMark).append(" ");

            }
            jsonObject.addProperty("Fields:" + i + "-" + (i+2), rowOutput.toString());
        }
        return jsonObject.toString();
    }
}
