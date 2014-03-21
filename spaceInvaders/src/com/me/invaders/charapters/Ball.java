package com.me.invaders.charapters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.me.invaders.spaceInvaders;

public class Ball { // Bola que si la coge la nave le da habilidades especiales.
	private static final float SPEED = 1; // Velocidad de la bola.
	
	private spaceInvaders invaders;
	private Texture texturaBola;
	private Vector2 posicion;
	private float anchura, altura;
	private Rectangle bordes;
	
	public Ball(spaceInvaders invaders, Vector2 posicion) {
		this.invaders = invaders;
		this.posicion = posicion;
		texturaBola = invaders.getManager().get("data/bolaBalaEspecial.png", Texture.class);
		anchura = texturaBola.getWidth();
		altura = texturaBola.getHeight();
		bordes = new Rectangle(posicion.x, posicion.y, anchura, altura);
	}
	
	public void draw(SpriteBatch batch) {
		batch.draw(texturaBola, posicion.x, posicion.y, anchura, altura);
	}
	
	public void update() { // Comportamiento de la bola.
		posicion.y = posicion.y - SPEED;
		
		// Actualizamos el borde y
		bordes.x = posicion.x;
		bordes.y = posicion.y;
	}
	
	public boolean colisionConNave(Ship nave) {
		return bordes.overlaps(nave.getBordes());
	}
	
	public void setPosicion(float x, float y) { // Permite cambiar la posición de la bola
		posicion.x = x;
		posicion.y = y;
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
