package com.mygdx.game;

import static com.mygdx.game.GameSettings.STARTING_ENEMY_APPEARANCE_COOL_DOWN;

import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;

public class GameSession {

    public GameState state;
    private int score;
    int kills;
    long nextEnemySpawnTime;
    long sessionStartTime;

    public GameSession() {
    }

    public void startGame() {
        state = GameState.PLAYING;
        score = 0;
        kills = 0;
        sessionStartTime = TimeUtils.millis();
        nextEnemySpawnTime = sessionStartTime + (long) (STARTING_ENEMY_APPEARANCE_COOL_DOWN * getEnemyPeriodCoolDown());
        System.out.println((TimeUtils.millis() - sessionStartTime) / 1000f + " --- " + (nextEnemySpawnTime - TimeUtils.millis()) + " --- " + (STARTING_ENEMY_APPEARANCE_COOL_DOWN * getEnemyPeriodCoolDown()));
    }

    public void killRegistration() {
        kills += 1;
    }

    public void updateScore() {
        score = (int) (TimeUtils.millis() - sessionStartTime) / 100 + kills * 100;
    }

    public int getScore() {
        return score;
    }

    public void pauseGame() {
        state = GameState.PAUSED;
    }

    public void resumeGame() {
        state = GameState.PLAYING;
    }

    public void endGame() {
        updateScore();
        state = GameState.ENDED;
        ArrayList<Record> recordsTable = MemoryManager.loadRecordsTable();
        if (recordsTable == null) {
            recordsTable = new ArrayList<>();
        }
        recordsTable.add(new Record(getScore(), MemoryManager.loadUserName()));



        MemoryManager.saveTableOfRecords(recordsTable);
    }

    public boolean shouldSpawnTrash() {
        if (nextEnemySpawnTime <= TimeUtils.millis()) {
            nextEnemySpawnTime = TimeUtils.millis() + (long) (STARTING_ENEMY_APPEARANCE_COOL_DOWN * getEnemyPeriodCoolDown());
            return true;
        }
        return false;
    }

    private float getEnemyPeriodCoolDown() {
        return (float) Math.exp(-0.001 * (TimeUtils.millis() - sessionStartTime + 1) / 1000);
    }
}
