package com.tiktac.toe.validator;

import com.tiktac.toe.domain.Game;
import com.tiktac.toe.domain.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GameValidatorTest {

    private GameValidator gameValidator;
    private Game game;
    private Player player;

    @BeforeEach
    void setUp() {
        this.game = mock(Game.class);
        this.player = mock(Player.class);
        this.gameValidator = new GameValidator();
    }

    @Test
    void isValid() {
        long playerId = 123L;

        when(this.game.isFinished()).thenReturn(false);
        when(this.game.isValidPlayer(player)).thenReturn(true);
        when(this.game.getCurrentPlayerTurnId()).thenReturn(playerId);
        when(this.player.getPlayerId()).thenReturn(playerId);

        boolean result = this.gameValidator.isValid(Optional.ofNullable(this.game), this.player);

        assertTrue(result);
    }

    @Test
    void getErrors() {
        this.gameValidator.isValid(Optional.ofNullable(this.game), this.player);
        List<String> errors = this.gameValidator.getErrors();

        assertTrue(errors.size() > 0);
    }

}