package com.me.invaders.bottons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.me.invaders.spaceInvaders;

public class BotonPlay extends Boton {

	public BotonPlay(spaceInvaders invaders, Vector2 posicion) {
		super(invaders, posicion);
		Textura = invaders.getManager().get("data/BotonPlay.png", Texture.class);
		asignarBordes();
	}
	
	@Override
	protected void funcionamiento() {
		invaders.setScreen(invaders.JUEGO);
	}
}
