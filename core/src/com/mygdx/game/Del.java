package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

import static com.mygdx.game.Constants.PPM;

public class Del extends BaseScreen {
    World world;
    Stage stage;
    Box2DDebugRenderer renderer;
    Hueta h;

    public Del(MyGdxGame myGdxGame) {
        super(myGdxGame);
    }

    @Override
    public void show() {

        world = new World(new Vector2(0,-15f), false);
        stage = new Stage(new FitViewport(480/PPM,654/PPM));
        renderer = new Box2DDebugRenderer();

        h = new Hueta(world, new Vector2(2,2));
        stage.addActor(new Land(world));
        stage.addActor(h);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render(world, stage.getCamera().combined);
        stage.act(delta);

        world.step(delta,6,2);

        stage.draw();


        asdas();

    }

    private void asdas() {
        if (Gdx.input.isTouched()){
            stage.addActor(new Hueta(world,new Vector2(Gdx.input.getX()/PPM,
                    Gdx.graphics.getHeight()/PPM - Gdx.input.getY()/PPM)));
        }
    }

    @Override
    public void dispose() {


    }
}
