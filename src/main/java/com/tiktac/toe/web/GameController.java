package com.tiktac.toe.web;

import com.tiktac.toe.domain.Player;
import com.tiktac.toe.dto.JoinGameRequest;
import com.tiktac.toe.dto.MakeMoveRequest;
import com.tiktac.toe.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/game")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("create")
    public ResponseEntity<?> createGame (@AuthenticationPrincipal Player player) throws Exception {
        long gameId = this.gameService.createGame(player);
        if (gameId > 0) {
            return ResponseEntity.ok("XD");
        }
        return ResponseEntity.badRequest().body("NOT XD");
    }

    @PostMapping("join")
    public ResponseEntity<?> joinGame (@RequestBody JoinGameRequest joinGameRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean gameJoined = this.gameService.joinGame(
                joinGameRequest.getGameId(),
                (Player) authentication.getPrincipal()
                );

        return gameJoined ? ResponseEntity.ok("Joined the game") : ResponseEntity.badRequest().body("didnt join");
    }

    @PostMapping("move")
    public ResponseEntity<?> makeMove(@RequestBody MakeMoveRequest moveRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String moveMade = this.gameService.makeMove(moveRequest, (Player) authentication.getPrincipal());

        return ResponseEntity.ok().body(moveMade);
    }
}
