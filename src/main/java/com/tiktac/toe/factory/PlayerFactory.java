package com.tiktac.toe.factory;

import com.tiktac.toe.domain.player.Player;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class PlayerFactory implements FactoryBean<Player> {
    @Override
    public Player getObject() throws Exception {
        return new Player();
    }

    @Override
    public Class<?> getObjectType() {
        return Player.class;
    }
}
