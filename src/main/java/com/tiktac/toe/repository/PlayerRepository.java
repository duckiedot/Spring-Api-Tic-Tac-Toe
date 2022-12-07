package com.tiktac.toe.repository;

import com.tiktac.toe.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByUsername(String usernameFromToken);

    Optional<Player> findByPlayerId(Long playerId);
}
