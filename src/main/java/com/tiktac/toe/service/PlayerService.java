package com.tiktac.toe.service;

import com.tiktac.toe.domain.Player;
import com.tiktac.toe.dto.player.RegisterPlayerRequest;
import com.tiktac.toe.factory.PlayerFactory;
import com.tiktac.toe.repository.PlayerRepository;
import com.tiktac.toe.util.CustomPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerFactory playerFactory;

    private final PasswordEncoder passwordEncoder;

    public PlayerService(
            PlayerRepository playerRepository,
            PlayerFactory playerFactory,
            CustomPasswordEncoder customPasswordEncoder
    ) {
        this.playerRepository = playerRepository;
        this.playerFactory = playerFactory;
        this.passwordEncoder = customPasswordEncoder.getPasswordEncoder();
    }

    public boolean createPlayer(RegisterPlayerRequest createPlayerRequest) throws Exception {
        Player newPlayer = this.playerFactory.getObject();
        assert newPlayer != null;
        newPlayer.setUsername(createPlayerRequest.getUsername());
        newPlayer.setPassword(this.passwordEncoder.encode(createPlayerRequest.getPassword()));
        this.playerRepository.save(newPlayer);
        return true;
    }

    public int getPlayerWins(Long playerId) {
        Optional<Player> player = this.playerRepository.findByPlayerId(playerId);
        return player.get().getGamesWon();
    }
}
