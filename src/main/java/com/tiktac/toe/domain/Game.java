package com.tiktac.toe.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long gameId;

    @Column(nullable = false)
    private boolean isFinished = false;

    @OneToMany
    private List<Player> players;

    @OneToOne
    private Player startingPlayer;

    @OneToOne
    private Board board;

    @Column(nullable = false)
    private Long currentPlayerTurnId;

    public boolean isValidPlayer(Player player) {
        return this.players.contains(player);
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public boolean isGameFull() {
        return this.players.size() > 1;
    }
}
