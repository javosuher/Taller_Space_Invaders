package com.me.invaders.charapters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.me.invaders.spaceInvaders;

public class AlienBonus { // Nuevo alien que si se le dispara se obtiene una puntuación mayor
	private spaceInvaders invaders;
	private Texture texturaAlienBonus;
	private Vector2 posicion;
	private float anchura, altura;
	private Rectangle bordes;
	private float velocidad;
	
	public AlienBonus(spaceInvaders invaders, Vector2 posicion, float velocidad) {
		this.posicion = posicion;
		this.invaders = invaders;
		texturaAlienBonus = invaders.getManager().get("data/naveAlienBonus.png", Texture.class);
		this.anchura = texturaAlienBonus.getWidth();
		this.altura = texturaAlienBonus.getHeight();
		bordes = new Rectangle(posicion.x, posicion.y, anchura, altura);
		this.velocidad = velocidad;
	}
	
	public void draw(SpriteBatch batch) { // Dibuja la nave
		batch.draw(texturaAlienBonus, posicion.x, posicion.y, anchura, altura);
	}
	
	public void update() {
		posicion.x = posicion.x + velocidad;
		
		// Actualizamos los bordes (la anchura y altura ya se definen en el constructor de Entidad)
		bordes.x = posicion.x;
	}
	
	public boolean Muerto(Shot disparo) {
		return colisiona(bordes, disparo.getBordes());
	}
	
	private boolean colisiona(Rectangle a, Rectangle b) {
		return a.overlaps(b); // la funcion overlaps nos devuelve verdadero si dos rectangulos se solapan.
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
