package com.mygdx.game.components;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameSettings;

import java.util.ArrayList;

public class RecordsListView {

    float x;
    float y;

    BitmapFont font;
    String recordsListString;

    public RecordsListView(BitmapFont font, float y) {
        this.y = y;
        x = 0;
        this.font = font;
        recordsListString = "";
    }

    public void setRecords(ArrayList<Integer> recordsList) {
        recordsListString = "";
        int countOfRows = Math.min(recordsList.size(), 5);
        for (int i = recordsList.size() - 1; i >= recordsList.size() - countOfRows; i--) {
            System.out.println(recordsList.get(i));
            recordsListString += (i + 1) + ". - " + recordsList.get(i) + "\n";
        }

        GlyphLayout glyphLayout = new GlyphLayout(font, recordsListString);
        x = (GameSettings.SCREEN_WIDTH - glyphLayout.width) / 2;
    }

    public void draw(SpriteBatch batch) {
        font.draw(batch, recordsListString, x, y);
    }

    public void dispose() {
        font.dispose();
    }

}
