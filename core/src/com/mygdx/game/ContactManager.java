package com.mygdx.game;

import static com.mygdx.game.GameSettings.LASER_BULLET_BIT;
import static com.mygdx.game.GameSettings.SPACE_SHIP_BIT;
import static com.mygdx.game.GameSettings.TRASH1_BIT;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

public class ContactManager {

    World world;

    public ContactManager(World world) {
        this.world = world;

        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {

                Fixture fixA = contact.getFixtureA();
                Fixture fixB = contact.getFixtureB();

                int cDef = fixA.getFilterData().categoryBits;
                int cDef2 = fixB.getFilterData().categoryBits;


                if (cDef == TRASH1_BIT && cDef2 == LASER_BULLET_BIT) {
                    ((TrashObject) fixA.getUserData()).hit();
                    ((LaserBullet) fixB.getUserData()).wasHit = true;
                } else if (cDef2 == TRASH1_BIT && cDef == LASER_BULLET_BIT) {
                    ((TrashObject) fixB.getUserData()).hit();
                    ((LaserBullet) fixA.getUserData()).wasHit = true;
                }

                if (cDef == TRASH1_BIT && cDef2 == SPACE_SHIP_BIT) {
                    ((TrashObject) fixA.getUserData()).hit();
                    ((SpaceShip) fixB.getUserData()).hit();
                } else if (cDef2 == TRASH1_BIT && cDef == SPACE_SHIP_BIT) {
                    ((TrashObject) fixB.getUserData()).hit();
                    ((SpaceShip) fixA.getUserData()).hit();
                }

            }

            @Override
            public void endContact(Contact contact) {
            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });

    }

}
