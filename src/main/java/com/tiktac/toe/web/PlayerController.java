package com.tiktac.toe.web;

import com.tiktac.toe.dto.player.RegisterPlayerRequest;
import com.tiktac.toe.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/player")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("create")
    public ResponseEntity<?> createUser(@RequestBody RegisterPlayerRequest registerPlayerRequest) throws Exception {
        boolean userCreated = this.playerService.createPlayer(registerPlayerRequest);

        return userCreated ? ResponseEntity.ok("ok") : ResponseEntity.badRequest().body("not ok");
    }

    @GetMapping("get/wins/{playerId}")
    public int getPlayerWins(@PathVariable("playerId") Long playerId) {
        return this.playerService.getPlayerWins(playerId);
    }
}
