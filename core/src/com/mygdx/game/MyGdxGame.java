package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.screens.GameScreen;

import static com.mygdx.game.GameSettings.*;

public class MyGdxGame extends Game {

    float accumulator = 0;

    public SpriteBatch batch;
    public OrthographicCamera camera;

    public Vector3 touch;
    public World world;
    public Box2DDebugRenderer debugRenderer;

    public BitmapFont largeWhiteFont;
    public BitmapFont commonWhiteFont;
    public BitmapFont commonBlackFont;

    public GameScreen gameScreen;

    @Override
    public void create() {

        Box2D.init();
        debugRenderer = new Box2DDebugRenderer();
        world = new World(new Vector2(0, 0), true);

        largeWhiteFont = FontBuilder.generate(200, Color.WHITE, "spaceagecyrillic_regular.ttf");
        commonWhiteFont = FontBuilder.generate(100, Color.WHITE, "spaceagecyrillic_regular.ttf");
        commonBlackFont = FontBuilder.generate(100, Color.BLACK, "spaceagecyrillic_regular.ttf");


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

    public void stepWorld() {
        float delta = Gdx.graphics.getDeltaTime();
        accumulator += Math.min(delta, 0.25f);

        if (accumulator >= STEP_TIME) {
            accumulator -= STEP_TIME;
            world.step(STEP_TIME, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
        }
    }
}
