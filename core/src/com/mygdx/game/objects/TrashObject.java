package com.mygdx.game.objects;

import static com.mygdx.game.GameSettings.TRASH1_BIT;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.GameSettings;

import java.util.Random;

public class TrashObject extends GameObject {

    private static final float paddingHorizontal = 30f;
    int lifeLeft;

    public TrashObject(World world) {
        super(
                "trash1.png",
                140 / 2 + paddingHorizontal +
                        (new Random()).nextInt((int) (GameSettings.SCREEN_WIDTH - 2 * paddingHorizontal - 140)),
                GameSettings.SCREEN_HEIGHT + 100 / 2,
                140, 100,
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

    public boolean isInFrame() {
        return getY() + height / 2 > 0;
    }
}
