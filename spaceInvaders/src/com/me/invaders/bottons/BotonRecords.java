package com.me.invaders.bottons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.me.invaders.spaceInvaders;

public class BotonRecords extends Boton {

	public BotonRecords(spaceInvaders invaders, Vector2 posicion) {
		super(invaders, posicion);
		Textura = invaders.getManager().get("data/BotonRecords.png", Texture.class);
		asignarBordes();
	}
	
	@Override
	protected void funcionamiento() {
		invaders.setScreen(invaders.RECORDS);
	}
}
