package com.me.invaders.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.me.invaders.spaceInvaders;

public class GameOverScreen extends AbstractScreen { // Pantalla que su utilidad es mostrar el GameOver. 
	private Texture TexturaFondo;
	private int contador; // Para que no se pueda pulsar desde el principio.
	
	public GameOverScreen(spaceInvaders invaders) {
		super(invaders);
	}
	
	@Override
	public void show() {
		TexturaFondo = invaders.getManager().get("data/GameOver.png", Texture.class);
		contador = 200;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		if(Gdx.input.isTouched() && contador == 0)
			invaders.setScreen(invaders.PRINCIPAL);  // Te devuelve a la pantalla del menú
		else if(contador > 0)
			contador--;
		
		batch.begin();
		batch.draw(TexturaFondo, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}
}
