package com.tiktac.toe.handler;

import com.tiktac.toe.domain.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GameHandlerTest {

    private GameHandler gameHandler;
    private Board board;

    //Test all cases for win, horizontal, vertical, diagonal
    public static Stream<Arguments> charArrayProvider() {
        return Stream.of(
                Arguments.of((Object) new char[]{'x', 'o', 'x', 'o', 'x', 'x', 'x', 'x', 'x'}),
                Arguments.of((Object) new char[]{'x', 'o', 'o', 'x', 'o', 'o', 'x', 'o', 'o'}),
                Arguments.of((Object) new char[]{'x', 'o', 'o', 'o', 'x', 'o', 'o', 'o', 'x'})
        );
    }

    @BeforeEach
    void setUp() {
        this.gameHandler = new GameHandler();
        this.board = mock(Board.class);
    }

    @ParameterizedTest
    @MethodSource("charArrayProvider")
    void isWinner(char[] chars) {
        char[] charBoard = new char[]{'x', 'o', 'x', 'o', 'x', 'x', 'x', 'x', 'x'};
        when(board.getBoard()).thenReturn(charBoard);
        boolean result = this.gameHandler.isWinner(board);
        assertTrue(result);
    }
}