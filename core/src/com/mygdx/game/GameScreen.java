package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

import static com.mygdx.game.Constants.PPM;

public class GameScreen extends BaseScreen {
    World world;
    Box2DDebugRenderer render;
    OrthographicCamera camera;

    Land land;
    Player player;
    float dx, dy;
    OrthogonalTiledMapRenderer rr;

    Stage stage;
    Ball ball;

    float impulsY = 100f;


    public GameScreen(MyGdxGame myGdxGame) {
        super(myGdxGame);
    }

    @Override
    public void show() {
        super.show();

        stage = new Stage();

        world = new World(new Vector2(0, -1f), false);
        render = new Box2DDebugRenderer();
        camera = new OrthographicCamera(myGdxGame.WW / PPM, myGdxGame.WH / PPM);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

        float unitScale = 1 / PPM;
        TiledMap map = new TmxMapLoader().load("map/untitled.tmx");


        rr = new OrthogonalTiledMapRenderer(map, unitScale);

        player = new Player(world);

        stage.addActor(player);
        BodyDef bdef = new BodyDef();
        Body body;
        FixtureDef fdef = new FixtureDef();

        PolygonShape shape = new PolygonShape();
        createWindows(map, bdef, fdef, shape);

        ball = new Ball(world, new Vector2(3, 2));
        stage.addActor(ball);


        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {


//                if (contact.getFixtureA().getBody()== ball.body ||
//                        contact.getFixtureB().getBody()== player.body){
//                    System.out.println("Action");
//
//
//
//                }

            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {
                if (contact.getFixtureA().getBody() == (ball.getBody()) &&
                        contact.getFixtureB().getBody() == player.body ||
                        contact.getFixtureB().getBody() == (ball.getBody()) &&
                                contact.getFixtureA().getBody() == player.body
                        ) {

                    float pw = 480/PPM - (480/PPM -0.7f);
                    if (player.body.getPosition().x - ball.body.getPosition().x > 0 &&
                            player.body.getPosition().x - ball.body.getPosition().x <= pw
                            ) ball.body.applyForceToCenter(player.body.getPosition().x*5f - ball.body.getPosition().x, impulsY, true);

                    else{
                        ball.body.applyForceToCenter(-player.body.getPosition().x*5f + ball.body.getPosition().x, impulsY, true);
                    }


                }

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });


    }

    private void createWindows(TiledMap map, BodyDef bdef, FixtureDef fdef, PolygonShape shape) {
        Body body;
        for (RectangleMapObject object : map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)) {

            Rectangle rect = object.getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() / PPM + rect.width / PPM / 2), (rect.getY() / PPM + rect.height / PPM / 2));

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / PPM / 2, rect.getHeight() / PPM / 2);
            fdef.shape = shape;
            body.createFixture(fdef);

        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cameraUpdate(delta);
        camera.update();
        rr.setView(camera);
        rr.render();

        stage.act(delta);
        world.step(delta, 6, 2);
        stage.draw();
        render.render(world, camera.combined);
        touch();


    }

    private void cameraUpdate(float delta) {
        if (ball.body.getPosition().y> 5){
            camera.position.y = ball.body.getPosition().y;
        }else{
            camera.position.y = camera.viewportHeight/2;
        }
    }

    private void touch() {
        if (Gdx.input.isTouched()) {
            player.body.setTransform(Gdx.input.getX() / PPM,
//Gdx.graphics.getHeight()/PPM - Gdx.input.getY()/PPM,
                    player.body.getPosition().y,
                    player.body.getAngle());

        }
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
