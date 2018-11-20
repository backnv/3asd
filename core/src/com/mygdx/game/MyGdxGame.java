package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class MyGdxGame extends Game {
	int WW = 480;
	int WH = 720;

	@Override
	public void create () {


		setScreen(new Del(this));


	}




}
