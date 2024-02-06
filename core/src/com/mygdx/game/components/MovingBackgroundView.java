package com.mygdx.game.components;

import static com.mygdx.game.GameSettings.SCALE;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class MovingBackgroundView {

    Texture texture;

    float texture1Y, texture2Y;
    float speed = 0.06f;

    public MovingBackgroundView(String pathToTexture) {
        texture1Y = 0;
        texture2Y = 1280 * SCALE;
        texture = new Texture(pathToTexture);
    }

    public void move() {
        texture1Y -= speed;
        texture2Y -= speed;

        if (texture1Y <= -1280 * SCALE) {
            texture1Y = 1280 * SCALE;
        }
        if (texture2Y <= -1280 * SCALE) {
            texture2Y = 1280 * SCALE;
        }
    }

    public void draw(Batch batch) {
        batch.draw(texture, 0, texture1Y, 720 * SCALE, 1280 * SCALE);
        batch.draw(texture, 0, texture2Y, 720 * SCALE, 1280 * SCALE);
    }

    public void dispose() {
        texture.dispose();
    }

}
