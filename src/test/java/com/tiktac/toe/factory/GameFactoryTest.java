package com.tiktac.toe.factory;

import com.tiktac.toe.domain.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameFactoryTest {

    private GameFactory gameFactory;

    @BeforeEach
    void setUp() {
        this.gameFactory = new GameFactory();
    }

    @Test
    void getObject() throws Exception {
        assertInstanceOf(
                Game.class,
                this.gameFactory.getObject()
        );
    }

    @Test
    void getObjectType() {
        assertSame(
                Game.class,
                this.gameFactory.getObjectType()
                );
    }
}