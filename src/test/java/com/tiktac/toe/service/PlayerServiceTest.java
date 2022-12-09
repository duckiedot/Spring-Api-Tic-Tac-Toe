package com.tiktac.toe.service;

import com.tiktac.toe.domain.player.Player;
import com.tiktac.toe.dto.player.RegisterPlayerRequest;
import com.tiktac.toe.factory.PlayerFactory;
import com.tiktac.toe.repository.PlayerRepository;
import com.tiktac.toe.util.CustomPasswordEncoder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerServiceTest {

    private PlayerService playerService;
    private PlayerRepository playerRepository;
    private PlayerFactory playerFactory;
    private CustomPasswordEncoder customPasswordEncode;
    private Player player;
    private RegisterPlayerRequest registerPlayerRequest;
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        this.playerRepository = mock(PlayerRepository.class);
        this.playerFactory = mock(PlayerFactory.class);
        this.customPasswordEncode = mock(CustomPasswordEncoder.class);
        this.player = mock(Player.class);
        this.registerPlayerRequest = mock(RegisterPlayerRequest.class);
        this.passwordEncoder = mock(PasswordEncoder.class);

        when(this.customPasswordEncode.getPasswordEncoder()).thenReturn(this.passwordEncoder);


        this.playerService = new PlayerService(
                this.playerRepository,
                this.playerFactory,
                this.customPasswordEncode
        );
    }

    @Test
    void createPlayer() throws Exception {
        String username = "test123";
        String password = "pass123";

        when(this.playerFactory.getObject()).thenReturn(this.player);
        when(this.registerPlayerRequest.getUsername()).thenReturn(username);
        when(this.registerPlayerRequest.getPassword()).thenReturn(password);

        boolean result = this.playerService.createPlayer(this.registerPlayerRequest);
    }

    @Test
    void getPlayerWins() {
        long playerId = 222L;
        int gamesWon = 5;

        when(this.playerRepository.findByPlayerId(playerId)).thenReturn(Optional.ofNullable(this.player));
        when(this.player.getGamesWon()).thenReturn(gamesWon);

        int result = this.playerService.getPlayerWins(playerId);

        assertEquals(result, gamesWon);
    }
}