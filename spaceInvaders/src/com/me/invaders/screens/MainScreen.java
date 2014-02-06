package com.me.invaders.screens;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.me.invaders.spaceInvaders;
import com.me.invaders.bottons.*;

// Pantalla que su finalidad es mostrar el menú principal.

public class MainScreen extends AbstractScreen {
	private List<Boton> botones; // Vector de botones

	public MainScreen(spaceInvaders invaders) {
		super(invaders);
		
		// Creamos el vector de botones y añadimos los botones que se quieren mostrar
		botones = new ArrayList<Boton>();
		int enMedioDeLaPantalla = Gdx.graphics.getWidth() / 2 - invaders.getManager().get("data/BotonPlay.png", Texture.class).getWidth() / 2;
		botones.add(new BotonPlay(invaders, new Vector2(enMedioDeLaPantalla, Gdx.graphics.getHeight() / 2 + 50)));
		botones.add(new BotonRecords(invaders, new Vector2(enMedioDeLaPantalla, Gdx.graphics.getHeight() / 2 - 60)));
		botones.add(new BotonExit(invaders, new Vector2(enMedioDeLaPantalla, Gdx.graphics.getHeight() / 2 - 170)));
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		for(Boton boton : botones)
			boton.update();
		
		batch.begin();
		batch.draw(invaders.getManager().get("data/Background.png", Texture.class), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		for(Boton boton : botones)
			boton.draw(batch);
		batch.end();
	}

	@Override
	public void show() {
		invaders.setMarcadorDePuntos(0); // Ponemos los puntos a 0;
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
