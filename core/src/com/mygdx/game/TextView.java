package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TextView {

    float x;
    float y;

    BitmapFont font;

    TextView(BitmapFont font, float x, float y) {
        this.font = font;
        this.x = x;
        this.y = y;
    }

    void draw(String text, SpriteBatch batch) {
        GlyphLayout glyphLayout = new GlyphLayout(font, text);
        font.draw(batch, text, x, y + glyphLayout.height);
    }

    void dispose() {
        font.dispose();
    }

}
