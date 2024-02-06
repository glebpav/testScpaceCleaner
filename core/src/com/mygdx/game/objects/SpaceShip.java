package com.mygdx.game.objects;

import static com.mygdx.game.GameSettings.SHOOTING_COOL_DOWN;
import static com.mygdx.game.GameSettings.SPACE_SHIP_BIT;
import static com.mygdx.game.GameSettings.SCALE;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.objects.GameObject;

public class SpaceShip extends GameObject {

    public int lifeLeft;
    long lastShotTime;
    boolean isShootingAvailable = true;

    public SpaceShip(float x, float y, World world) {
        super("ship.png", x, y, 150 * SCALE, 150 * SCALE, SPACE_SHIP_BIT, world);
        body.setLinearDamping(2);
        lifeLeft = 3;
        lastShotTime = TimeUtils.millis();
    }

    public void move(Vector3 vector3) {

        body.applyForceToCenter(new Vector2((vector3.x - body.getPosition().x) * 40,
                (vector3.y - body.getPosition().y) * 40), true);

        if (body.getPosition().y > (1280 * SCALE / 2f - height / 2f)) {
            setY((1280 * SCALE / 2f - height / 2f));

            body.applyForceToCenter(
                    new Vector2(
                            body.getLinearVelocity().x,
                            Math.min(0, (vector3.y - body.getPosition().y) * 10)
                    ), true
            );

            body.setLinearVelocity(
                    new Vector2(
                            body.getLinearVelocity().x,
                            0
                    )
            );
        }

        if (body.getPosition().y <= (height / 2f) ) {
            setY(height / 2);

            body.applyForceToCenter(
                    new Vector2(
                            body.getLinearVelocity().x,
                            0
                    ), true
            );

            body.setLinearVelocity(
                    new Vector2(
                            body.getLinearVelocity().x,
                            0
                    )
            );
        }

        if (body.getPosition().x < (- width / 2f)) {
            setX(720 * SCALE);
        }

        if (body.getPosition().x > (720 * SCALE + width / 2f)) {
            setX(0);
        }

    }

    public boolean needToShoot() {
        if (isShootingAvailable && TimeUtils.millis() - lastShotTime >= SHOOTING_COOL_DOWN) {
            lastShotTime = TimeUtils.millis();
            return true;
        }
        return false;
    }

    public void hit() {
        lifeLeft -= 1;
    }

}
