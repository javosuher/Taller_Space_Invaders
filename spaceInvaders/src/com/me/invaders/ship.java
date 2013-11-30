package com.me.invaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class ship {
	private static final float SPEED = 20; // Velocidad de movimiento de la nave.
	private static final float MARGEN_IZQUIERDO = 10; // Margen izquierdo que no puede pasar la nave.
	private static final float MARGEN_DERECHO = 936; // Margen derecho que no puede pasar la nave.
		
	private Vector2 posicion;
	private float anchura, altura;
	private Rectangle bordes;
		
	public ship(Vector2 posicion, float anchura, float altura) {
		this.posicion = posicion;
		this.anchura = anchura;
		this.altura = altura;
		bordes = new Rectangle(posicion.x, posicion.y, anchura, altura);
	}

	public boolean update () { // Funcion que se ejecuta en Render de GameScren, y son las opciones que puede tener la nave
		boolean disparo = false;
		if(Gdx.input.isKeyPressed(Keys.RIGHT) && NoChoqueBordeDerecha()) {
			posicion.x = posicion.x + SPEED;// a la posicion en x le sumo la velocidad que hemos determinado por SPEED.
				
		}
			
		if(Gdx.input.isKeyPressed(Keys.LEFT) && NoChoqueBordeIzquierda()) {
			posicion.x = posicion.x - SPEED; // lo mismo pero restandole, ya que vamos a la izquierda
				
		}
			
		//Si se dispara
		if(Gdx.input.isKeyPressed(Keys.SPACE)) {
				 disparo = true;
		}
			
		// Actualizamos los bordes (la anchura y altura ya se definen en el constructor de Entidad
		bordes.x = posicion.x;
		bordes.y = posicion.y;
		
		return disparo; // Devuelve true si ha disparado la nave.
	}
	
	private boolean NoChoqueBordeDerecha() { // Permite que la nave no siga avanzando y salirse de la pantalla por la derecha.
		return posicion.x + anchura < MARGEN_DERECHO;
	}
	private boolean NoChoqueBordeIzquierda() { // Permite que la nave no siga avanzando y salirse de la pantalla por la izquierda.
		return posicion.x > MARGEN_IZQUIERDO;
	}
		
	// Getters -----------------------------------

	public static float getSpeed() {
		return SPEED;
	}

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
