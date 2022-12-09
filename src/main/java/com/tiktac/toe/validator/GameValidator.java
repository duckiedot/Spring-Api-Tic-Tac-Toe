package com.tiktac.toe.validator;

import com.tiktac.toe.domain.Game;
import com.tiktac.toe.domain.player.Player;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class GameValidator {

    @Getter
    private final List<String> errors = new ArrayList<>();

    //Return true only if all validators are passed
    public boolean isValid(Optional<Game> game, Player player) {
        this.errors.clear();

        if (game.isEmpty()) {
            this.errors.add("No game with matching ID found");
            return false;
        }
        Game foundGame = game.get();

        return this.gameActive(foundGame)
                && this.gameCorrectPlayer(foundGame, player)
                && this.gameCorrectTurn(foundGame, player);
    }

    private boolean gameActive(Game game) {
        if (!game.isFinished()) {
            return true;
        }
        this.errors.add("Game is already finished");
        return false;
    }

    private boolean gameCorrectPlayer(Game game, Player player) {
        if (game.isValidPlayer(player)) {
            return true;
        }
        this.errors.add("Given player does not belong here");
        return false;
    }

    private boolean gameCorrectTurn(Game game, Player player) {
        if (Objects.equals(game.getCurrentPlayerTurnId(), player.getPlayerId())) {
            return true;
        }
        this.errors.add("It's not your turn");
        return false;
    }
}
