package com.me.invaders.charapters;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.me.invaders.spaceInvaders;

public class Ship {
	private static final float SPEED = 20; // Velocidad de movimiento de la nave.
	private static final float MARGEN_IZQUIERDO = 10; // Margen izquierdo que no puede pasar la nave.
	private static final float MARGEN_DERECHO = Gdx.graphics.getWidth() - 10; // Margen derecho que no puede pasar la nave.
	
	private Texture texturaShip;
	private Vector2 posicion;
	private float anchura, altura;
	private Rectangle bordes;
		
	public Ship(spaceInvaders invaders, Vector2 posicion) {
		texturaShip = invaders.getManager().get("data/ship.png", Texture.class);
		texturaShip.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		this.posicion = posicion;
		this.anchura = texturaShip.getWidth();
		this.altura = texturaShip.getHeight();
		bordes = new Rectangle(posicion.x, posicion.y, anchura, altura);
	}
	
	public void draw(SpriteBatch batch) { // Dibuja la nave
		batch.draw(texturaShip, posicion.x, posicion.y, anchura, altura);
	}
	
	public boolean update() { // Funcion que se ejecuta en Render de GameScren, y son las opciones que puede tener la nave
		boolean disparo = false;
		
		if(Gdx.app.getType() == ApplicationType.Desktop) // Si estamos jugando en nuestro ordenador
			disparo = entradaDesktop();
		else if(Gdx.app.getType() == ApplicationType.Android) // Si se esta ejecutando en android
			disparo = entradaAndroid();
			
		// Actualizamos los bordes (la anchura y altura ya se definen en el constructor de Entidad
		bordes.x = posicion.x;
		bordes.y = posicion.y;
		
		return disparo; // Devuelve true si ha disparado la nave.
	}
	
	private boolean entradaDesktop() { // Acciones de la nave en Desktop
		if(Gdx.input.isKeyPressed(Keys.RIGHT) && NoChoqueBordeDerecha())
			posicion.x = posicion.x + SPEED;// a la posicion en x le sumo la velocidad que hemos determinado por SPEED.	
		if(Gdx.input.isKeyPressed(Keys.LEFT) && NoChoqueBordeIzquierda())
			posicion.x = posicion.x - SPEED; // lo mismo pero restandole, ya que vamos a la izquierda
			
		//Si se dispara
		if(Gdx.input.isKeyPressed(Keys.SPACE))
			return true;
		else 
			return false;
	}
	private boolean entradaAndroid() { // Acciones de la nave en Android
		
		if(Gdx.input.isTouched()) { // Si se da con el dedo en la pantalla
			if(Gdx.input.getX() < MARGEN_IZQUIERDO) // Si se pasa de la pantalla por la izquierda
				posicion.x = MARGEN_IZQUIERDO;
			else if(Gdx.input.getX() + anchura > MARGEN_DERECHO) // Si se pasa de la pantalla por la derecha
				posicion.x = MARGEN_DERECHO - anchura;
			else
				posicion.x = Gdx.input.getX() - (anchura / 2);
		}
			
		//Si se dispara
		if(Gdx.input.isTouched(1)) // Si se da con un segundo dedo en la pantalla
			return true;
		else 
			return false;
	}
	
	private boolean NoChoqueBordeDerecha() { // Permite que la nave no siga avanzando y salirse de la pantalla por la derecha.
		return posicion.x + anchura < MARGEN_DERECHO;
	}
	private boolean NoChoqueBordeIzquierda() { // Permite que la nave no siga avanzando y salirse de la pantalla por la izquierda.
		return posicion.x > MARGEN_IZQUIERDO;
	}
	
	public boolean tocadoPorDisparo(Shot disparo) {
		return colisiona(bordes, disparo.getBordes());
	}
	
	private boolean colisiona(Rectangle a, Rectangle b) {
		return a.overlaps(b); // la funcion overlaps nos devuelve verdadero si dos rectangulos se solapan.
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
