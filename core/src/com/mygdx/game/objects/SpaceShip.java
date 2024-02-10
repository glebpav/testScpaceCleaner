package com.mygdx.game.objects;

import static com.mygdx.game.GameSettings.SHOOTING_COOL_DOWN;
import static com.mygdx.game.GameSettings.SPACE_SHIP_BIT;
import static com.mygdx.game.GameSettings.SCALE;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
        super("ship.png", x, y, 150, 150, SPACE_SHIP_BIT, world);
        body.setLinearDamping(2);
        lifeLeft = 3;
        lastShotTime = TimeUtils.millis();
    }

    public void move(Vector3 vector3) {

        body.applyForceToCenter(new Vector2((vector3.x - getX()) * 4,
                (vector3.y - getY()) * 4), true);

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

    public boolean isAlive() {
        return lifeLeft > 0;
    }

    private void putInFrame() {
        if (getY() > (1280 / 2f - height / 2f)) {
            setY((1280 / 2f - height / 2f));
        }

        if (getY() <= (height / 2f) ) {
            setY(height / 2);
        }

        if (getX() < (- width / 2f)) {
            setX(720);
        }

        if (getX() > (720 + width / 2f)) {
            setX(0);
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        putInFrame();
        super.draw(batch);
    }
}
