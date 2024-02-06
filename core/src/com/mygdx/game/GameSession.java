package com.mygdx.game;

import static com.mygdx.game.GameSettings.STARTING_ENEMY_APPEARANCE_COOL_DOWN;

import com.badlogic.gdx.utils.TimeUtils;

public class GameSession {

    GameStatus status;
    long nextEnemySpawnTime;
    long sessionStartTime;

    public GameSession() {

    }

    public void startGame() {
        status = GameStatus.PLAYING;
        sessionStartTime = TimeUtils.millis();
        nextEnemySpawnTime = sessionStartTime + (long) (STARTING_ENEMY_APPEARANCE_COOL_DOWN * getEnemyPeriodCoolDown());
        System.out.println((TimeUtils.millis() - sessionStartTime) / 1000f + " --- " + (nextEnemySpawnTime - TimeUtils.millis()) + " --- " + (STARTING_ENEMY_APPEARANCE_COOL_DOWN * getEnemyPeriodCoolDown()));
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
