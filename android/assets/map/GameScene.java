package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;

class GameScene {

    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer renderer;

    private OrthographicCamera gamecam;
    private FitViewport gamePort;

    private int dx;

    World world;
    Box2DDebugRenderer b2dr;
    Mario mario;


    public GameScene() {


        tiledMap = new TmxMapLoader().load("scenes/level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(tiledMap);


        gamecam = new OrthographicCamera(300, 158);

        gamePort = new FitViewport(300, 158, gamecam);
        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0, 0), true);
        b2dr = new Box2DDebugRenderer();

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        for (RectangleMapObject object : tiledMap.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = object.getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2), (rect.getY() + rect.getHeight() / 2));

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2, rect.getHeight() / 2);
            fdef.shape = shape;
            body.createFixture(fdef);

        }
        for (RectangleMapObject object : tiledMap.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = object.getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2), (rect.getY() + rect.getHeight() / 2));

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2, rect.getHeight() / 2);
            fdef.shape = shape;
            body.createFixture(fdef);

        }

        mario = new Mario(world);


    }

    public void render() {


        renderer.render();

        b2dr.render(world, gamecam.combined);
        update();
    }

    public void update() {
        gamecam.update();
        mario.update();
        renderer.setView(gamecam);
        input();
//        port.update((int)port.getWorldWidth() / 2, (int) port.getWorldHeight() / 2);

        world.step(1 / 60f, 6, 2);


    }

    private void input() {
        if (Gdx.input.isTouched()) {
//            gamecam.position.x+=3;
            if (Gdx.input.getX() < Gdx.graphics.getWidth() / 2) {
                gamecam.position.x -= 3;
            } else {
                gamecam.position.x += 3;
            }
        }
    }
}
