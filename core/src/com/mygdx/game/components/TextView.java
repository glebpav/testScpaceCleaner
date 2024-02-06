package com.mygdx.game.components;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TextView {

    float x;
    float y;

    BitmapFont font;

    public TextView(BitmapFont font, float x, float y) {
        this.font = font;
        this.x = x;
        this.y = y;
    }

    public void draw(String text, SpriteBatch batch) {
        GlyphLayout glyphLayout = new GlyphLayout(font, text);
        font.draw(batch, text, x, y + glyphLayout.height);
    }

    public void dispose() {
        font.dispose();
    }

}
