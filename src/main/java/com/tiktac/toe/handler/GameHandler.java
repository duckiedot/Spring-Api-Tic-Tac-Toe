package com.tiktac.toe.handler;

import com.tiktac.toe.domain.Board;
import org.springframework.stereotype.Component;

@Component
public class GameHandler {
    public boolean isWinner(Board board) {
        char[] charBoard = board.getBoard();

        return this.horizontalVictory(charBoard)
                || this.verticalVictory(charBoard)
                || this.diagonalVictory(charBoard);
    }

    private boolean horizontalVictory(char[] board) {
        for (int i = 0; i < 9; i += 3) {
            if (board[i] == board[i+1] && board[i] == board[i+2]) {
                return true;
            }
        }
        return false;
    }

    private boolean verticalVictory(char[] board) {
        for (int i = 0; i < 3; i++) {
            if (board[i] == board[i+3] && board[i] == board[i+6]) {
                return true;
            }
        }
        return false;
    }

    private boolean diagonalVictory(char[] board) {
        return (board[0] == board[4] && board[0] == board[8])
                || (board[2] == board[4] && board[2] == board[6]);
    }
}
