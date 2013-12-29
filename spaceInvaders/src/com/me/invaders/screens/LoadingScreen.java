package com.me.invaders.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.me.invaders.spaceInvaders;

public class LoadingScreen extends AbstractScreen {

	public LoadingScreen(spaceInvaders invaders) {
		super(invaders);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		if(invaders.getManager().update()) { // Si han cargado todas las imagenes
			invaders.setScreen(invaders.PRINCIPAL);
		}
		
		if(invaders.getManager().isLoaded("data/Loading.png", Texture.class)) { // Si se ha cargado la imagen Loading.png
			batch.begin();
			batch.draw(invaders.getManager().get("data/Loading.png", Texture.class), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			batch.end();
		}
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
	}
	@Override
	public void hide() {
		// TODO Auto-generated method stub
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}
}
