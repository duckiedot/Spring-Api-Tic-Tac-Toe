package com.tiktac.toe.handler;

import com.tiktac.toe.domain.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class BoardHandlerTest {

    private BoardHandler boardHandler;

    public static Stream<Arguments> boardProvider() {
        return Stream.of(
                Arguments.of(4, new char[]{'o', 'o', 'o', 'o', 'x', 'o', 'o', 'o', 'o'}),
                Arguments.of(8, new char[]{'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'x'}),
                Arguments.of(0, new char[]{'x', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o'})
                );
    }

    @BeforeEach
    void setUp() {
        this.boardHandler = new BoardHandler();
    }

    @ParameterizedTest
    @MethodSource("boardProvider")
    void setBoardField(int fieldId, char[] expectedBoard) {
        Board board = new Board();
        board.setBoard(new char[]{'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o'});
        board = this.boardHandler.setBoardField(board, fieldId, 'x');

        assertArrayEquals(expectedBoard, board.getBoard());
    }
}