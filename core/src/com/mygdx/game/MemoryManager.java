package com.mygdx.game;

import static com.mygdx.game.GameSettings.DEFAULT_USER_NAME;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Json;

import java.util.ArrayList;

public class MemoryManager {

    private static final Preferences preferences = Gdx.app.getPreferences("User saves");

    public static void saveTableOfRecords(ArrayList<Integer> table) {

        Json json = new Json();
        String tableInString = json.toJson(table);

        // FileHandle file = Gdx.files.local("table.json");
        // file.writeString(tableInString, true);

        preferences.putString("recordsTable", tableInString);
        preferences.flush();
    }

    public static ArrayList<Integer> loadRecordsTable() {
        if (!preferences.contains("recordsTable"))
            return null;

        // FileHandle file = Gdx.files.local("scores.json");
        // String scores = file.readString();

        String scores = preferences.getString("recordsTable");

        Json json = new Json();
        ArrayList<Integer> table = json.fromJson(ArrayList.class, scores);
        return table;
    }

    public static void saveSoundSettings(boolean isOn) {
        preferences.putBoolean("isSoundOn", isOn);
        preferences.flush();
    }

    public static boolean loadIsSoundOn() {
        return preferences.getBoolean("isSoundOn", true);
    }

    public static void saveMusicSettings(boolean isOn) {
        preferences.putBoolean("isMusicOn", isOn);
        preferences.flush();
    }

    public static boolean loadIsMusicOn() {
        return preferences.getBoolean("isMusicOn", true);
    }

}
