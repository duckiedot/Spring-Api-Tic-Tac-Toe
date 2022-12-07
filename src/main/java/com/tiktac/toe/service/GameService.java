package com.tiktac.toe.service;

import com.tiktac.toe.domain.Board;
import com.tiktac.toe.domain.Game;
import com.tiktac.toe.domain.Player;
import com.tiktac.toe.dto.MakeMoveRequest;
import com.tiktac.toe.factory.GameFactory;
import com.tiktac.toe.handler.BoardHandler;
import com.tiktac.toe.handler.GameHandler;
import com.tiktac.toe.repository.GameRepository;
import com.tiktac.toe.validator.GameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class GameService {

    private final GameValidator gameValidator;
    private final GameRepository gameRepository;
    private final GameFactory gameFactory;
    private final GameHandler gameHandler;
    private final BoardHandler boardHandler;

    @Autowired
    public GameService(
            GameRepository gameRepository,
            GameFactory gameFactory,
            GameValidator gameValidator,
            BoardHandler boardHandler,
            GameHandler gameHandler
    ) {
        this.gameRepository = gameRepository;
        this.gameFactory = gameFactory;
        this.gameValidator = gameValidator;
        this.boardHandler = boardHandler;
        this.gameHandler = gameHandler;
    }

    //Creates a game and assigns the player to it. Also, it also sets the game creator as starting player
    public long createGame(Player player) throws Exception {
        Game newGame = this.gameFactory.getObject();
        assert newGame != null;

        newGame.addPlayer(player);
        newGame.setCurrentPlayerTurnId(player.getPlayerId());
        this.gameRepository.save(newGame);

        return newGame.getGameId();
    }

    //If the game exists, and is not full, adds player into the game
    public boolean joinGame(long gameId, Player player) {
        Optional<Game> game = this.gameRepository.findByGameId(gameId);

        if (game.isEmpty()) {
            return false;
        }

        Game joinedGame = game.get();

        if (!joinedGame.isGameFull()) {
            joinedGame.addPlayer(player);
            this.gameRepository.save(joinedGame);
            return true;
        }
        return false;
    }

    //if allowed by validator, proceed to make a move
    public String makeMove(MakeMoveRequest moveRequest, Player currentPlayer) {
        Optional<Game> game = this.gameRepository.findByGameId(moveRequest.getGameId());
        if (!gameValidator.isValid(game, currentPlayer)) {
            return gameValidator.getErrors().toString();
        }
        Game foundGame = game.get();

        //Sets the next player turn to the other player
        List<Player> gamePlayers = foundGame.getPlayers();
        gamePlayers.remove(currentPlayer);
        foundGame.setCurrentPlayerTurnId(gamePlayers.stream().findFirst().get().getPlayerId());

        Board updatedBoard = this.boardHandler.setBoardField(
                foundGame.getBoard(),
                moveRequest.getRowId(),
                moveRequest.getColumnId(),
                Objects.equals(foundGame.getStartingPlayer().getPlayerId(), currentPlayer.getPlayerId()) ? 'X' : 'O'
        );

        foundGame.setBoard(updatedBoard);
        this.gameRepository.save(foundGame);

        return "XD";
    }
}
