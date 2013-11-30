package com.me.invaders;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class shot {
	private static final float SPEED = 5; // Velocidad del disparo.
	
	private Vector2 posicion;
	private float anchura, altura;
	private Rectangle bordes;
	
	public shot(Vector2 posicion, float anchura, float altura){
		this.posicion = posicion;
		this.anchura = anchura;
		this.altura = altura;
		bordes = new Rectangle(posicion.x, posicion.y, anchura, altura);
	}
	
	public void update() {
		posicion.y = posicion.y + SPEED;
		
		// Actualizamos el borde y
		bordes.y = posicion.y;
	}
	
	public void alienMuerto() {
		posicion.y = 946; // Si se mata al alien enviamos el disparo fuera de la pantalla.
	}
	
	// Getters -----------------------------------
	
	public Vector2 getPosicion() {
		return posicion;
	}

	public float getAnchura() {
		return anchura;
	}

	public float getAltura() {
		return altura;
	}

	public Rectangle getBordes() {
		return bordes;
	}
}
