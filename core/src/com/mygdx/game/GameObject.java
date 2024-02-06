package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class GameObject {

    public short cBits = 0;

    float width;
    float height;

    Body body;
    Texture texture;

    GameObject(String texturePath, float x, float y, float width, float height, short cBits, World world) {
        this.width = width;
        this.height = height;
        this.cBits = cBits;

        texture = new Texture(texturePath);
        body = createBody(x, y, world);
    }

    void draw(SpriteBatch batch) {
        batch.draw(texture,
                body.getPosition().x - (width / 2f),
                body.getPosition().y - (height / 2f),
                width,
                height);
    }

    boolean isHit() {
        return false;
    }

    void move() {

    }

    public void setX(float x) {
        body.setTransform(x, body.getPosition().y, 0);
    }

    public void setY(float y) {
        body.setTransform(body.getPosition().x, y, 0);
    }

    private Body createBody(float x, float y, World world) {
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.fixedRotation = true;
        Body body = world.createBody(def);

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(Math.max(width, height) / 2f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circleShape;
        fixtureDef.density = 0.1f;
        fixtureDef.friction = 1f;
        fixtureDef.filter.categoryBits = cBits;

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this);
        circleShape.dispose();

        body.setTransform(x, y, 0);
        return body;
    }

}
