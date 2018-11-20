package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

class Land extends Actor {
    World world;
    Body body;

    public Land(World world) {
        this.world = world;

        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.KinematicBody;
        bdef.position.set(0,0);

        body = world.createBody(bdef);

        Vector2[] vector2s = new Vector2[4];
        vector2s[0] = new Vector2(0,0);
        vector2s[1] = new Vector2(6.6f,0);
        vector2s[2] = new Vector2(6.6f,.5f);
        vector2s[3] = new Vector2(0,.5f);
        PolygonShape shape = new PolygonShape();
        shape.set(vector2s);

        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;

        body.createFixture(fdef);





        PolygonShape shape1 = new PolygonShape();
        shape1.setAsBox(.1f,20);

        FixtureDef fdef1 = new FixtureDef();
        fdef1.shape = shape1;
        body.createFixture(fdef1);




        bdef.position.x =4;
        PolygonShape shape2 = new PolygonShape();
        shape2.setAsBox(1,1);

        FixtureDef fdef2 = new FixtureDef();
        fdef2.shape = shape2;
        body.createFixture(fdef2);





        shape.dispose();
    }
}
