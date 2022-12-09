package com.tiktac.toe.factory;

import com.tiktac.toe.domain.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerFactoryTest {

    private PlayerFactory playerFactory;

    @BeforeEach
    void setUp() {
        this.playerFactory = new PlayerFactory();
    }

    @Test
    void getObject() throws Exception {
        assertInstanceOf(
                Player.class,
                this.playerFactory.getObject()
        );
    }

    @Test
    void getObjectType() {
        assertSame(
                Player.class,
                this.playerFactory.getObjectType()
        );
    }
}