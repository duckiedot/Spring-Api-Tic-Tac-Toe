package com.tiktac.toe.domain.player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Authority implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @JsonIgnore
    @ManyToMany(mappedBy = "authRoles", fetch = FetchType.EAGER)
    protected Set<Player> users = new HashSet<>();

    @Column
    protected String roleName;

    @Override
    public String getAuthority() {
        return this.roleName;
    }
}
