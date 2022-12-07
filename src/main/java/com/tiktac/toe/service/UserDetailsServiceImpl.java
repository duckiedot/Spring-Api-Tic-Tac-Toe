package com.tiktac.toe.service;

import com.tiktac.toe.domain.Player;
import com.tiktac.toe.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PlayerRepository playerRepository;

    @Autowired
    public UserDetailsServiceImpl(
            PlayerRepository playerRepository
    ) {
        this.playerRepository = playerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Player> userOptional = this.playerRepository.findByUsername(username);

        return userOptional.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }
}
