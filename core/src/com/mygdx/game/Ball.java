package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.mygdx.game.Constants.PPM;

public class Ball  extends Actor {
    World world;
    Body body;
    Texture t = new Texture("badlogic.jpg");

    public Body getBody() {
        return body;
    }

    public Ball(World world, Vector2 pos) {
        this.world = world;

        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set(pos);

        body = world.createBody(bdef);

        CircleShape shape = new CircleShape();
        shape.setRadius(0.2f);


        FixtureDef fdef = new FixtureDef();

        fdef.shape = shape;
        body.setGravityScale(2f);
        fdef.density = 1f;
        fdef.friction = 10f;
        fdef.restitution = 1f;

        body.setUserData("ball");
        body.createFixture(fdef);


        shape.dispose();
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition(body.getPosition().x*PPM , body.getPosition().y*PPM );
        setSize(t.getWidth()/5,t.getHeight()/5);

        batch.draw(new Sprite(t),getX(),
                getY(),getOriginX(),
                getOriginY(),
                getWidth(),
                getHeight(),
                getScaleX(),
                getScaleY(),
                getRotation());

    }
}

