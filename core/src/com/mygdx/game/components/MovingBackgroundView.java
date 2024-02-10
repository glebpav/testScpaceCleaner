package com.mygdx.game.components;

import static com.mygdx.game.GameSettings.SCALE;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class MovingBackgroundView {

    Texture texture;

    float texture1Y, texture2Y;
    float speed = 2f;

    public MovingBackgroundView(String pathToTexture) {
        texture1Y = 0;
        texture2Y = 1280;
        texture = new Texture(pathToTexture);
    }

    public void move() {
        texture1Y -= speed;
        texture2Y -= speed;

        if (texture1Y <= -1280) {
            texture1Y = 1280;
        }
        if (texture2Y <= -1280) {
            texture2Y = 1280;
        }
    }

    public void draw(Batch batch) {
        batch.draw(texture, 0, texture1Y, 720, 1280);
        batch.draw(texture, 0, texture2Y, 720, 1280);
    }

    public void dispose() {
        texture.dispose();
    }

}
