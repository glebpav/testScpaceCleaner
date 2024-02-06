package com.mygdx.game;

import static com.mygdx.game.MyGdxGame.SCALE;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ImageView {

    float x;
    float y;

    float width;
    float height;

    Texture texture;

    ImageView(float x, float y, String imagePath) {
        this.x = x;
        this.y = y;
        texture = new Texture(imagePath);
        this.width = texture.getWidth() * SCALE;
        this.height = texture.getHeight() * SCALE;
    }

    void draw(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height);
    }

    void dispose() {
        texture.dispose();
    }


}
