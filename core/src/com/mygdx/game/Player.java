package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.mygdx.game.Constants.PPM;

public class Player extends Actor {
    World world;
    Body body;
    Texture t = new Texture("badlogic.jpg");

    public Player(World world) {
        this.world = world;

        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.KinematicBody;
        bdef.position.set(1,1);

        body = world.createBody(bdef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1.4f/2,.4f/2);

        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;

//        fdef.restitution = 5f;
//        fdef.density = 1f;

//        body.setUserData("player");

        body.setUserData("player");
        body.createFixture(fdef);


        shape.dispose();
    }

}
