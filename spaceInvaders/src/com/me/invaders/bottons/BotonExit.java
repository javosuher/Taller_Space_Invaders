package com.me.invaders.bottons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.me.invaders.spaceInvaders;

public class BotonExit extends Boton {

	public BotonExit(spaceInvaders invaders, Vector2 posicion) {
		super(invaders, posicion);
		Textura = invaders.getManager().get("data/BotonExit.png", Texture.class);
		asignarBordes();
	}
	
	@Override
	protected void funcionamiento() {
		Gdx.app.exit();
	}
}
