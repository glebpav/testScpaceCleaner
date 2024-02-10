package com.mygdx.game.objects;

import static com.mygdx.game.GameSettings.TRASH1_BIT;
import static com.mygdx.game.GameSettings.SCALE;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.objects.GameObject;

import java.util.Random;

public class TrashObject extends GameObject {

    private static final float paddingHorizontal = 30f;
    int lifeLeft;

    public TrashObject(World world) {
        super(
                "trash1.png",
                (140 / 2) + paddingHorizontal +
                        (new Random()).nextInt((int) (720 - 2 * paddingHorizontal - 140)),
                1280 + 100 / 2,
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
}
