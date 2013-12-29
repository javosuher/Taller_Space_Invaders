package com.me.invaders.charapters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.me.invaders.spaceInvaders;

public class Shot {
	private static final float SPEED = 5; // Velocidad del disparo.
	
	private Texture texturaShot;
	private Sound shotSound, explosion;
	private Vector2 posicion;
	private float anchura, altura;
	private Rectangle bordes;
	
	public Shot(spaceInvaders invaders, Vector2 posicion) {
		texturaShot = invaders.getManager().get("data/shot.png", Texture.class);
		shotSound = invaders.getManager().get("data/shot.wav", Sound.class); // Añade el sonido de los disparos.
		explosion = invaders.getManager().get("data/explosion.wav", Sound.class); // Añade el sonido de los aliens que mueren
		this.posicion = posicion;
		this.anchura = texturaShot.getWidth();
		this.altura = texturaShot.getHeight();
		bordes = new Rectangle(posicion.x, posicion.y, anchura, altura);
	}
	
	public void draw(SpriteBatch batch) {
		batch.draw(texturaShot, posicion.x, posicion.y, anchura, altura);
	}
	
	public void update() {
		posicion.y = posicion.y + SPEED;
		
		// Actualizamos el borde y
		bordes.y = posicion.y;
	}
	
	public void disparoSonido() {
		shotSound.play();
	}
	
	public void alienMuerto() {
		posicion.y = Gdx.graphics.getHeight(); // Si se mata al alien enviamos el disparo fuera de la pantalla.
		explosion.play(); // Sonido de explosion porque ha muerto un alien.
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
