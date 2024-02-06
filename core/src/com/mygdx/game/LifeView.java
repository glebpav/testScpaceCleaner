package com.mygdx.game;

import static com.mygdx.game.MyGdxGame.SCALE;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LifeView {

    float x;
    float y;

    float width;
    float height;

    Texture texture;

    LifeView(float x, float y) {
        this.x = x;
        this.y = y;
        texture = new Texture("life.png");
        this.width = texture.getWidth() * SCALE;
        this.height = texture.getHeight() * SCALE;
    }

    void draw(int leftLive, SpriteBatch batch) {
        if (leftLive > 0) batch.draw(texture, x + (texture.getWidth() + 6) * SCALE, y, width, height);
        if (leftLive > 1) batch.draw(texture, x, y, width, height);
        if (leftLive > 2) batch.draw(texture, x + 2 * (texture.getWidth() + 6) * SCALE, y, width, height);
    }

    void dispose() {
        texture.dispose();
    }


}
