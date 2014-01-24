package com.me.invaders.bottons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.me.invaders.spaceInvaders;

//Clase abstracta, que van a heredar botones que creemos, para poder poner botones en la pantalla.

public abstract class Boton {
	protected spaceInvaders invaders;
	protected Vector2 posicion; // Donde se posiciona el boton
	protected Rectangle bordes; 
	protected float xMinima; // Estos atributos sirven para poner las coordenadas para pulsar el boton.
	protected float yMinima;
	protected float xMaxima;
	protected float yMaxima;

	//Atributos para pintar
	protected Texture Textura;
	
	public Boton(spaceInvaders invaders, Vector2 posicion) {
		this.invaders = invaders;
		this.posicion = posicion;
	}
	
	protected void asignarBordes() { // Metodo que permite asignar los bordes del botón para su correcto funcionamiento.
		bordes = new Rectangle(posicion.x, posicion.y, Textura.getWidth(), Textura.getHeight());
	
		xMinima = posicion.x;
		yMaxima = Gdx.graphics.getHeight() - posicion.y;
		xMaxima = posicion.x + bordes.width;
		yMinima = Gdx.graphics.getHeight() - (posicion.y + bordes.height);
	}
	
	public void draw(SpriteBatch batch) {
		batch.draw(Textura, posicion.x, posicion.y, bordes.width, bordes.height);
	}
	
	public void update() {
		if(sePulsaElBoton())
			funcionamiento();
	}
	private boolean sePulsaElBoton() { // Esta función privada sirve para comprobar si se pulsa el botón.
		return Gdx.input.isTouched() && Gdx.input.getX() >= xMinima && Gdx.input.getX() <= xMaxima 
				&& Gdx.input.getY() >= yMinima && Gdx.input.getY() <= yMaxima;
	}
	
	protected abstract void funcionamiento(); // Método que implementaran las clases que hereden y le pondrán el comportamiento deseado
	
	// Getters and Setters ------------------------------------------------------------------------

	public Vector2 getPosicion() {
		return posicion;
	}
	
	public void setPosicion(Vector2 posicion) {
		this.posicion = posicion;
	}

	public Rectangle getBordes() {
		return bordes;
	}

	public void setBordes(Rectangle bordes) {
		this.bordes = bordes;
	}
}
