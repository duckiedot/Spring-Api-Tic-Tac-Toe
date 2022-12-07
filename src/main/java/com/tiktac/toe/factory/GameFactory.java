package com.tiktac.toe.factory;

import com.tiktac.toe.domain.Game;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class GameFactory implements FactoryBean<Game> {
    @Override
    public Game getObject() throws Exception {
        return new Game();
    }

    @Override
    public Class<?> getObjectType() {
        return Game.class;
    }
}
