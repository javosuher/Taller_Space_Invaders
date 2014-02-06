package com.me.invaders.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.me.invaders.spaceInvaders;

// Pantalla que su finalidad es mostrar la puntuación que ha obtenido el usuario.

public class RecordScreen extends AbstractScreen {

	public RecordScreen(spaceInvaders invaders) {
		super(invaders);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		volverAlMenu(); // Permite volver al menú
		
		batch.begin();
		batch.draw(invaders.getManager().get("data/Records.png", Texture.class), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		// Dibujamos las posiciones.
		invaders.getFont().setColor(Color.MAGENTA); // Permite cambiar el color de la fuente.
		invaders.getFont().draw(batch, "RANK", Gdx.graphics.getWidth() / 2 - 150, Gdx.graphics.getHeight() / 2 + 100);
		invaders.getFont().setColor(Color.WHITE);
		invaders.getFont().draw(batch, "1ST", Gdx.graphics.getWidth() / 2 - 150, Gdx.graphics.getHeight() / 2 + 20);
		invaders.getFont().draw(batch, "2ND", Gdx.graphics.getWidth() / 2 - 150, Gdx.graphics.getHeight() / 2 - 60);
		invaders.getFont().draw(batch, "3RD", Gdx.graphics.getWidth() / 2 - 150, Gdx.graphics.getHeight() / 2 - 140);
		
		// Dibujamos en la pantalla los 3 mejores records
		invaders.getFont().setColor(Color.CYAN);
		invaders.getFont().draw(batch, "SCORE", Gdx.graphics.getWidth() / 2 + 50, Gdx.graphics.getHeight() / 2 + 100);
		invaders.getFont().setColor(Color.WHITE);
		invaders.getFont().draw(batch, Integer.toString(invaders.getPreferencias().getInteger("primerRecord", 0)), Gdx.graphics.getWidth() / 2 + 50, Gdx.graphics.getHeight() / 2 + 20);
		invaders.getFont().draw(batch, Integer.toString(invaders.getPreferencias().getInteger("segundoRecord", 0)), Gdx.graphics.getWidth() / 2 + 50, Gdx.graphics.getHeight() / 2 - 60);
		invaders.getFont().draw(batch, Integer.toString(invaders.getPreferencias().getInteger("tercerRecord", 0)), Gdx.graphics.getWidth() / 2 + 50, Gdx.graphics.getHeight() / 2 - 140);
		
		batch.end();
	}
	
	private void volverAlMenu() { // Método privado que tiene los botones para volver al menú durante el juego
		if(Gdx.input.isKeyPressed(Keys.ESCAPE) || Gdx.input.isKeyPressed(Keys.BACK) || Gdx.input.isKeyPressed(Keys.MENU))
			invaders.setScreen(invaders.PRINCIPAL);
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
