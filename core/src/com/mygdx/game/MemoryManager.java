package com.mygdx.game;

import static com.mygdx.game.GameSettings.DEFAULT_USER_NAME;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Json;

import java.util.ArrayList;

public class MemoryManager {

    private static final Preferences prefs = Gdx.app.getPreferences("User saves");

    public static void saveTableOfRecords(ArrayList<Record> table) {

        Json json = new Json();
        String tableInString = json.toJson(table);

        // FileHandle file = Gdx.files.local("table.json");
        // file.writeString(tableInString, true);

        prefs.putString("recordsTable", tableInString);
        prefs.flush();
    }

    public static ArrayList<Record> loadRecordsTable() {
        if (!prefs.contains("recordsTable"))
            return null;

        // FileHandle file = Gdx.files.local("scores.json");
        // String scores = file.readString();

        String scores = prefs.getString("recordsTable");

        Json json = new Json();
        ArrayList<Record> table = json.fromJson(ArrayList.class, scores);
        return table;
    }

    public static void saveUserName(String username) {
        prefs.putString("username", username);
        prefs.flush();
    }

    public static String loadUserName() {
        return prefs.getString("username", DEFAULT_USER_NAME);
    }

}
