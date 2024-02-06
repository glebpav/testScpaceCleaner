package com.mygdx.game;

import static com.mygdx.game.GameSettings.BULLET_VELOCITY;
import static com.mygdx.game.GameSettings.LASER_BULLET_BIT;
import static com.mygdx.game.GameSettings.TRASH1_BIT;
import static com.mygdx.game.MyGdxGame.SCALE;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import java.util.Random;

public class TrashObject extends GameObject {

    private static final float paddingHorizontal = 30f;
    int lifeLeft;

    public TrashObject(World world) {
        super(
                "trash1.png",
                (140 * SCALE / 2) + paddingHorizontal * SCALE +
                        (new Random()).nextInt((int) (720 - 2 * paddingHorizontal - 140)) * SCALE,
                1280 * SCALE + 100 * SCALE / 2,
                140 * SCALE, 100 * SCALE,
                TRASH1_BIT, world);

        lifeLeft = 1;

        body.setLinearVelocity(new Vector2(0, -20));
    }

    public void hit() {
        lifeLeft -= 1;
    }

    public boolean isAlive() {
        return lifeLeft > 0;
    }
}
