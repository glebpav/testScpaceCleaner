package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends Game {

    static final float STEP_TIME = 1f / 60f;
    static final int VELOCITY_ITERATIONS = 6;
    static final int POSITION_ITERATIONS = 6;
    static final float SCALE = 0.05f;

    float accumulator = 0;

    SpriteBatch batch;
    OrthographicCamera camera;

    Vector3 touch;
    World world;
    Box2DDebugRenderer debugRenderer;

    BitmapFont largeWhiteFont;
    BitmapFont commonWhiteFont;
    BitmapFont commonBlackFont;

    GameScreen gameScreen;

    @Override
    public void create() {

        Box2D.init();
        debugRenderer = new Box2DDebugRenderer();
        world = new World(new Vector2(0, 0), true);

        largeWhiteFont = FontBuilder.generate(48, Color.WHITE, "montserrat-bold.ttf");
        commonWhiteFont = FontBuilder.generate(100, Color.WHITE, "montserrat-bold.ttf");
        commonBlackFont = FontBuilder.generate(24, Color.BLACK, "montserrat-bold.ttf");


        gameScreen = new GameScreen(this);
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        touch = new Vector3();
        camera.setToOrtho(false, 720 * SCALE, 1280 * SCALE);


        setScreen(gameScreen);


    }

    @Override
    public void dispose() {
        batch.dispose();
        world.dispose();
        debugRenderer.dispose();
    }

    void stepWorld() {
        float delta = Gdx.graphics.getDeltaTime();
        accumulator += Math.min(delta, 0.25f);

        if (accumulator >= STEP_TIME) {
            accumulator -= STEP_TIME;
            world.step(STEP_TIME, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
        }
    }
}
