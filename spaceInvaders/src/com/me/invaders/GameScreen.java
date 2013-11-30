package com.me.invaders;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class GameScreen implements Screen{ // Implementa la interfaz de Screen, es decir, se comportara con las caracteristicas de una pantalla
	// sus funciones se llaman automaticamente cuando ocurre el evento al que estan asociadas (renderizar,
	//reescalar, pausar, resumir...) menos con dispose, para liberar los recursos hay que llamar a dispose manualmente
	
	private static final float LIMITE_DISPARO = 600; // La distancia que tiene que recorrer el disparo.
	private static final float MAXIMO_ALIENS_FILA = 8; // Numero maximo de aliens
	private static final float DISTANCIA = 50; // Distancia en el eje x donde se van colocando los aliens.
	private static final float ALTURA = 520; // Distancia en el eje y donde empiezan los aliens.
	private static final float MARGEN_IZQUIERDO = 50; // Limite de los aliens a la izquierda.
	private static final float MARGEN_DERECHO = 938; // Limite de los aliens a la derecha.
	
	private spaceInvaders invaders;
	private Texture TexturaFondo, texturaShip, texturaShot; // Una Texture es una clase que envuelve una textura estandar de OpenGL, se utiliza para imagenes simples.
	private Texture texturaAlien1, texturaAlien2, texturaAlien3, texturaAlien4; // Textura de los aliens
	private SpriteBatch batch; // "Grupo de Sprites (imagenes)" nos permite dibujar rectagulos como referencias a texturas, es necesario para mostrar todo por pantalla
	private ship nave; // Nave del juego.
	
	private ArrayList<alien> aliensTipo1; // Los aliens tipo1 del juego.
	private ArrayList<alien> aliensTipo2; // Los aliens tipo2 del juego.
	private ArrayList<alien> aliensTipo3; // Los aliens tipo3 del juego.
	private ArrayList<alien> aliensTipo4; // Los aliens tipo4 del juego.
	
	private shot disparo; // El disparo que matará los aliens.
	private Sound shotMusic; // Sonido cuando se dispara
	private Sound explosion; // Sonido cuando muere un alien
	private boolean actualizarDisparo; // Actualiza el disparo.
	private boolean gameOver; // Te indica si has perdido o no.
	

	public GameScreen(spaceInvaders invaders) {
		this.invaders = invaders;
		
		//Creamos las texturas, primero el fondo del juego.
		TexturaFondo = new Texture("data/Background.png"); // Asociamos la textura con la imagen correspondiente
		TexturaFondo.setFilter(TextureFilter.Linear, TextureFilter.Linear); // con setFilter controlamos la forma en la que la imagen se 
		//reescala, le añadimos el parametro TextureFilter.Linear en ambos casos, para que este reescalado sea lineal.
		
		// Creamos la nave
		texturaShip = new Texture("data/ship.png");
		texturaShip.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		nave = new ship(new Vector2(10,10), texturaShip.getWidth(), texturaShip.getHeight()); 
		//Creamos la nave en la posición de la panatalla que queremos(10, 10), y ancho y altura de la imagen.
		
		// Creamos los aliens y los introducimos en los ArrayList(Uno por los 4 tipos de alien que hay).
		aliensTipo1 = new ArrayList<alien>(); // Inicializamos los ArrayList
		aliensTipo2 = new ArrayList<alien>();
		aliensTipo3 = new ArrayList<alien>();
		aliensTipo4 = new ArrayList<alien>();
		
		// Creamos los aliens tipo1
		texturaAlien1 = new Texture("data/alien1.png");
		texturaAlien1.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		float distancia = DISTANCIA; // Distancia a la que tiene que estar cada alien en la pantalla.
		float altura = ALTURA; // La altura a la que tienen que estar los aliens en la pantalla.
		float limiteDerecha = MARGEN_DERECHO - ((texturaAlien1.getHeight() + 5) * MAXIMO_ALIENS_FILA); // Sirve para establecer el limite de cada alien por la derecha.
		float limiteIzquierda = MARGEN_IZQUIERDO; // Sirve para establecer el limite de cada alien por la izquierda.
		for(int i = 0; i < MAXIMO_ALIENS_FILA ; i++) {
			aliensTipo1.add(new alien(new Vector2(distancia, altura), texturaAlien1.getWidth(), texturaAlien1.getHeight(), limiteIzquierda, limiteDerecha));
			distancia = distancia + texturaAlien1.getHeight() + 5;
			limiteDerecha = limiteDerecha + texturaAlien1.getHeight() + 5;  // Actalizamos los 3 valores para crear el siguiente alien.
			limiteIzquierda = limiteIzquierda + texturaAlien1.getHeight() + 5;
		}
		altura = altura - texturaAlien1.getWidth() - 5; // Ponemos los valores adecuados para la siguiente fila de aliens.
		distancia = DISTANCIA;
		
		// Creamos los aliens tipo2
		texturaAlien2 = new Texture("data/alien2.png");
		texturaAlien2.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		limiteDerecha = MARGEN_DERECHO - ((texturaAlien2.getHeight() + 5) * MAXIMO_ALIENS_FILA);
		limiteIzquierda = MARGEN_IZQUIERDO;
		for(int i = 0; i < MAXIMO_ALIENS_FILA ; i++) {
			aliensTipo2.add(new alien(new Vector2(distancia, altura), texturaAlien2.getWidth(), texturaAlien2.getHeight(), limiteIzquierda, limiteDerecha));
			distancia = distancia + texturaAlien2.getHeight() + 5;
			limiteDerecha = limiteDerecha + texturaAlien2.getHeight() + 5;
			limiteIzquierda = limiteIzquierda + texturaAlien2.getHeight() + 5;
		}
		altura = altura - texturaAlien2.getWidth() - 5;
		distancia = DISTANCIA;
		
		// Creamos los aliens tipo3
		texturaAlien3 = new Texture("data/alien3.png");
		texturaAlien3.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		limiteDerecha = MARGEN_DERECHO - ((texturaAlien3.getHeight() + 5) * MAXIMO_ALIENS_FILA);
		limiteIzquierda = MARGEN_IZQUIERDO;
		for(int i = 0; i < MAXIMO_ALIENS_FILA ; i++) {
			aliensTipo3.add(new alien(new Vector2(distancia, altura), texturaAlien3.getWidth(), texturaAlien3.getHeight(), limiteIzquierda, limiteDerecha));
			distancia = distancia + texturaAlien3.getHeight() + 5;
			limiteDerecha = limiteDerecha + texturaAlien3.getHeight() + 5;
			limiteIzquierda = limiteIzquierda + texturaAlien3.getHeight() + 5;
		}
		altura = altura - texturaAlien3.getWidth() - 5;
		distancia = DISTANCIA;
		
		// Creamos los aliens tipo4
		texturaAlien4 = new Texture("data/alien4.png");
		texturaAlien4.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		limiteDerecha = MARGEN_DERECHO - ((texturaAlien4.getHeight() + 5) * MAXIMO_ALIENS_FILA);
		limiteIzquierda = MARGEN_IZQUIERDO;
		for(int i = 0; i < MAXIMO_ALIENS_FILA ; i++) {
			aliensTipo4.add(new alien(new Vector2(distancia, altura), texturaAlien4.getWidth(), texturaAlien4.getHeight(), limiteIzquierda, limiteDerecha));
			distancia = distancia + texturaAlien4.getHeight() + 5;
			limiteDerecha = limiteDerecha + texturaAlien4.getHeight() + 5;
			limiteIzquierda = limiteIzquierda + texturaAlien4.getHeight() + 5;
		}
		// Ya tenemos metidos los aliens en el ArrayList
		explosion = Gdx.audio.newSound(Gdx.files.internal("data/explosion.wav")); // Añade el sonido de los aliens que mueren
		
		// Imagen del disparo
		texturaShot = new Texture("data/shot.png");
		shotMusic = Gdx.audio.newSound(Gdx.files.internal("data/shot.wav")); // Añade el sonido de los disparos.
		actualizarDisparo = false;
		
		gameOver = false;
		batch = new SpriteBatch();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1); //Gdx es una clase con la que podemos acceder a variables que hacen referencia a todos los subsitemas, como son graficos, audio, ficheros, entrada y aplicaciones
		// gl es una variable de tipo GL, nos permite acceder a metodos de GL10, GL11 y GL20
		//En este caso glClearColor es un bucle (game loop) que establecera el fondo de la pantalla negro (0,0,0) con transparencia 1
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT); // Despues de la funcion anterior es necesario ejecutar esta, para que se lleve a cabo

		//Hacemos que se actualizen los parametros de la nave en la pantalla.
		boolean naveDispara = nave.update(); // Devuelve true si se ha disparado.
		
		// Creamos el disparo si se ha realizado
		if(naveDispara && !actualizarDisparo) {
			disparo = new shot(new Vector2(nave.getPosicion().x + (nave.getAnchura() / 2 - 1), nave.getAltura() + 12), texturaShot.getWidth(), texturaShot.getHeight());
			shotMusic.play(); // Sonido del disparo.
			actualizarDisparo = true; // Esta variable sirve para no hacer un disparo hasta que se termine el actual y para actualizar los parametros del mismo.
		}
		//Hacemos que se actualizen los parametros del disparo en la pantalla si hay efectuado uno.
		if(actualizarDisparo) {
			disparo.update();
			if(disparo.getPosicion().y + disparo.getAltura() > LIMITE_DISPARO) { // Cuando llegue al final de la pantalla
				actualizarDisparo = false;
			}
		}
		
		//Hacemos que se actualizen los parametros de los aliens de cada tipo
		for(int i = 0; i < aliensTipo1.size(); i++) {
			aliensTipo1.get(i).update();
			comprobarDerrota(aliensTipo1.get(i).getBordes().y); // Comprobamos si los aliens han llegado a la nave para indicar el "GameOver".
			if(actualizarDisparo && aliensTipo1.get(i).Muerto(disparo)) { // Si hay un disparo efectuado y le ha dado al alien
				aliensTipo1.remove(i); // Se destruye el alien!!
				explosion.play(); // Sonido de explosion porque ha muerto un alien.
				disparo.alienMuerto(); // Permite quitar el disparo de la pantalla
			}
		}
		for(int i = 0; i < aliensTipo2.size(); i++) {
			aliensTipo2.get(i).update();
			comprobarDerrota(aliensTipo2.get(i).getBordes().y); // Comprobamos si los aliens han llegado a la nave para indicar el "GameOver".
			if(actualizarDisparo && aliensTipo2.get(i).Muerto(disparo)) {
				aliensTipo2.remove(i);
				explosion.play(); // Sonido de explosion porque ha muerto un alien.
				disparo.alienMuerto();
			}
		}
		for(int i = 0; i < aliensTipo3.size(); i++) {
			aliensTipo3.get(i).update();
			comprobarDerrota(aliensTipo3.get(i).getBordes().y); // Comprobamos si los aliens han llegado a la nave para indicar el "GameOver".
			if(actualizarDisparo && aliensTipo3.get(i).Muerto(disparo)) {
				aliensTipo3.remove(i);
				explosion.play(); // Sonido de explosion porque ha muerto un alien.
				disparo.alienMuerto();
			}
		}
		for(int i = 0; i < aliensTipo4.size(); i++) {
			aliensTipo4.get(i).update();
			comprobarDerrota(aliensTipo4.get(i).getBordes().y); // Comprobamos si los aliens han llegado a la nave para indicar el "GameOver".
			if(actualizarDisparo && aliensTipo4.get(i).Muerto(disparo)) {
				aliensTipo4.remove(i);
				explosion.play(); // Sonido de explosion porque ha muerto un alien.
				disparo.alienMuerto();
			}
		}
		
		batch.begin(); // Aqui por fin comenzamos el renderizado
		//Dibujamos el fondo
		batch.draw(TexturaFondo, 0, 0, TexturaFondo.getWidth(), TexturaFondo.getHeight()); //La dibujamos en la esquina inferior derecha, tamaño natural
		//Dibujamos nuestra nave
		batch.draw(texturaShip, nave.getPosicion().x, nave.getPosicion().y, nave.getAnchura(), nave.getAltura());
		
		//Dibujamos los aliens
		for(alien alien : aliensTipo1) {
			batch.draw(texturaAlien1, alien.getPosicion().x, alien.getPosicion().y, alien.getAnchura(), alien.getAltura());
		}
		for(alien alien : aliensTipo2) {
			batch.draw(texturaAlien2, alien.getPosicion().x, alien.getPosicion().y, alien.getAnchura(), alien.getAltura());
		}
		for(alien alien : aliensTipo3) {
			batch.draw(texturaAlien3, alien.getPosicion().x, alien.getPosicion().y, alien.getAnchura(), alien.getAltura());
		}
		for(alien alien : aliensTipo4) {
			batch.draw(texturaAlien4, alien.getPosicion().x, alien.getPosicion().y, alien.getAnchura(), alien.getAltura());
		}
		
		//Dibujamos el disparo si hay alguno
		if(actualizarDisparo) { 
			batch.draw(texturaShot, disparo.getPosicion().x, disparo.getPosicion().y, disparo.getAnchura(), disparo.getAltura());
		}
		batch.end();
		// Terminamos el renderizado.
		
		if(estanTodosLosAliensDestruidos()) { // Ganaste!!
			invaders.setScreen(new WinScreen(invaders));
		}
		
		if(gameOver) { // Perdiste...	
			invaders.setScreen(new GameOverScreen(invaders));
		}
	}
	
	private boolean estanTodosLosAliensDestruidos() {
		// Si se han destruido todo los aliens.
		return (aliensTipo1.isEmpty() && aliensTipo2.isEmpty() && aliensTipo3.isEmpty() && aliensTipo4.isEmpty()); 
	}
	
	private void comprobarDerrota(float numero) { // Si algun alien ha llegado a la nave.
		if(numero < 50) {
			gameOver = true;
		}
	}

	@Override
	public void resize(int width, int height) {

	}
	@Override
	public void show() {
		// TODO Auto-generated method stub

	}
	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}
	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}
	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}
	@Override
	public void dispose() {
		//batch.dispose();
	}
}
