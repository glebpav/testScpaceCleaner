package com.mygdx.game;

import static com.mygdx.game.MyGdxGame.SCALE;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

public class GameScreen extends ScreenAdapter {

    GameSession gameSession;
    MyGdxGame myGdxGame;
    SpaceShip spaceShip;
    ArrayList<LaserBullet> bulletsArray;
    ArrayList<TrashObject> trashArray;

    LifeView lifeView;
    ImageView topBlackout;
    MovingBackground movingBackground;
    TextView scoreTextView;

    ContactManager contactManager;

    public GameScreen(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        bulletsArray = new ArrayList<>();
        trashArray = new ArrayList<>();
        gameSession = new GameSession();
        spaceShip = new SpaceShip((720 / 2f) * SCALE, 150 * SCALE, myGdxGame.world);

        contactManager = new ContactManager(myGdxGame.world);

        lifeView = new LifeView(305 * SCALE, 1215 * SCALE);
        topBlackout = new ImageView(0, 1180 * SCALE, "top_blackout.png");
        movingBackground = new MovingBackground("background1.png");
        scoreTextView = new TextView(myGdxGame.commonWhiteFont, 50 * SCALE, 1215 * SCALE);
    }

    @Override
    public void show() {
        System.out.println("start session");
        gameSession.startGame();
    }

    @SuppressWarnings("NewApi")
    @Override
    public void render(float delta) {

        if (Gdx.input.isTouched()) {
            myGdxGame.touch = myGdxGame.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            spaceShip.move(myGdxGame.touch);
        }

        if (gameSession.shouldSpawnTrash()) {
            TrashObject trashObject = new TrashObject(myGdxGame.world);
            trashArray.add(trashObject);
        }

        if (spaceShip.needToShoot()) {
            LaserBullet laserBullet = new LaserBullet(
                    spaceShip.body.getPosition().x,
                    (spaceShip.body.getPosition().y + spaceShip.height / 2),
                    myGdxGame.world
            );
            bulletsArray.add(laserBullet);
        }

        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        ScreenUtils.clear(Color.CLEAR);
        myGdxGame.stepWorld();

        myGdxGame.batch.begin();

        movingBackground.draw(myGdxGame.batch);
        spaceShip.draw(myGdxGame.batch);
        bulletsArray.forEach(laserBullet -> laserBullet.draw(myGdxGame.batch));
        trashArray.forEach(trashObject -> trashObject.draw(myGdxGame.batch));
        topBlackout.draw(myGdxGame.batch);
        lifeView.draw(spaceShip.lifeLeft, myGdxGame.batch);
        scoreTextView.draw("Score: " + 50, myGdxGame.batch);

        myGdxGame.batch.end();

        myGdxGame.debugRenderer.render(myGdxGame.world, myGdxGame.camera.combined);

        updateTrash();
        updateBullets();
        movingBackground.move();

    }

    private void updateTrash() {
        for (int i = 0; i < trashArray.size(); i++) {
            if (!trashArray.get(i).isAlive()) {
                myGdxGame.world.destroyBody(trashArray.get(i).body);
                trashArray.remove(i--);
            }
        }
    }

    private void updateBullets() {
        for (int i = 0; i < bulletsArray.size(); i++) {
            if (bulletsArray.get(i).hasToBeDestroyed()) {
                myGdxGame.world.destroyBody(bulletsArray.get(i).body);
                bulletsArray.remove(i--);
            }
        }
    }

    @Override
    public void dispose() {
        lifeView.dispose();
    }
}
