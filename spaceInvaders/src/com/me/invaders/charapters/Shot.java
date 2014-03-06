package com.me.invaders.charapters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.me.invaders.spaceInvaders;

public abstract class Shot { // Clase abstracta de los disparos
	protected Texture texturaShot;
	protected Sound shotSound, explosion;
	protected Vector2 posicion;
	protected float anchura, altura;
	protected Rectangle bordes;
	
	public Shot(spaceInvaders invaders, Vector2 posicion) {
		shotSound = invaders.getManager().get("data/shot.wav", Sound.class); // Añade el sonido de los disparos.
		explosion = invaders.getManager().get("data/explosion.wav", Sound.class); // Añade el sonido de los aliens que mueren
		this.posicion = posicion;
	}
	
	public void draw(SpriteBatch batch) {
		batch.draw(texturaShot, posicion.x, posicion.y, anchura, altura);
	}
	
	public abstract void update(); // Método que implementará cada tipo de disparo.
	
	public void disparoSonido() {
		shotSound.play();
	}
	
	public void alienMuerto() {
		posicion.y = Gdx.graphics.getHeight(); // Si se mata al alien enviamos el disparo fuera de la pantalla.
		explosion.play(); // Sonido de explosion porque ha muerto un alien.
	}
	public void naveTocada() {
		posicion.y = -100;
		explosion.play(); // Sonido de explosion porque se ha perdido.
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
