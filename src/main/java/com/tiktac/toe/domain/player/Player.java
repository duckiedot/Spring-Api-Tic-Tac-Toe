package com.tiktac.toe.domain.player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tiktac.toe.domain.Game;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Player implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long playerId;

    @Column
    private String username;

    @Column
    @JsonIgnore
    private String password;

    @Column
    private int gamesPlayed;

    @Column
    private int gamesWon;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name="user_authority",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "authority_id") }
    )
    private Set<Authority> authRoles = new HashSet<>();

    @ManyToMany(mappedBy = "players", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Game> games = new HashSet<>();

    public Player() {
        this.authRoles.add(new RegisteredRole());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authRoles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
