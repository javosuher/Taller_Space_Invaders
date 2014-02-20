package com.me.invaders.charapters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.me.invaders.spaceInvaders;

public class ShotShip extends Shot {
	private static final float SPEED = 5; // Velocidad del disparo.
	
	public ShotShip(spaceInvaders invaders, Vector2 posicion) {
		super(invaders, posicion);
		texturaShot = invaders.getManager().get("data/shot.png", Texture.class);
		this.anchura = texturaShot.getWidth();
		this.altura = texturaShot.getHeight();
		bordes = new Rectangle(posicion.x, posicion.y, anchura, altura);
	}
	
	public void update() { // Disparo de la nave
		posicion.y = posicion.y + SPEED;
		
		// Actualizamos el borde y
		bordes.y = posicion.y;
	}
}
