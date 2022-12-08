package com.tiktac.toe.domain;

import com.tiktac.toe.domain.player.Player;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
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

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "game_players",
            joinColumns = { @JoinColumn(name = "player_id") },
            inverseJoinColumns = { @JoinColumn(name = "game_id")}
    )
    private List<Player> players = new ArrayList<>();

    @OneToOne
    private Player startingPlayer;

    @OneToOne(cascade = CascadeType.ALL)
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
