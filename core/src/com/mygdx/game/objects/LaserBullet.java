package com.mygdx.game.objects;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.objects.GameObject;

import static com.mygdx.game.GameSettings.*;

public class LaserBullet extends GameObject {

    public boolean wasHit;

    public LaserBullet(float x, float y, World world) {
        super("bullet.png", x, y, 12, 44, LASER_BULLET_BIT, world);
        body.setLinearVelocity(new Vector2(0, BULLET_VELOCITY));
        body.setBullet(false);
        wasHit = false;
    }

    public boolean hasToBeDestroyed() {
        return wasHit || getY() - height / 2 > 1280;
    }
}
