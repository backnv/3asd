package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.mygdx.game.Constants.PPM;

class Hueta  extends Actor {
    Body body;
    Texture t = new Texture("badlogic.jpg");

    public Hueta(World world, Vector2 vector2) {
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set(vector2);

        body = world.createBody(bdef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1f/2,1f/2);

        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.density =1f;

        body.createFixture(fdef);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setRotation(body.getAngle() * 180 / (float) Math.PI);
        setPosition(body.getPosition().x - 1f / 2, body.getPosition().y - 1f / 2);
        setOrigin(1f / 2, 1f / 2);
        setSize(PPM,PPM );

        batch.draw(new Sprite(t),getX(),
                getY(),getOriginX(),
                getOriginY(),
                getWidth()/PPM,
                getHeight()/PPM,
                getScaleX(),
                getScaleY(),
                getRotation());
    }
}
