package com.mygdx.game;

import com.badlogic.gdx.utils.TimeUtils;

public class GameSettings {

    // Device settings

    public static final int SCREEN_WIDTH = 720;
    public static final int SCREEN_HEIGHT = 1280;

    // Physics settings

    public static final float STEP_TIME = 1f / 60f;
    public static final int VELOCITY_ITERATIONS = 6;
    public static final int POSITION_ITERATIONS = 6;
    public static final float SCALE = 0.05f;


    public static int SHOOTING_COOL_DOWN = 1000; // in [ms] - milliseconds
    public static int BULLET_VELOCITY = 200; // in [m/s] - meter per second

    public static long STARTING_ENEMY_APPEARANCE_COOL_DOWN = 2000; // in [ms] - milliseconds

    public static final short LASER_BULLET_BIT = 8;
    public static final short TRASH1_BIT = 2;
    public static final short SPACE_SHIP_BIT = 4;

    public static final String DEFAULT_USER_NAME = "Maksim";

}
