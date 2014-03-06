package com.me.invaders.screens;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.math.Vector2;
import com.me.invaders.spaceInvaders;
import com.me.invaders.charapters.*;

public class GameScreen extends AbstractScreen { 
	// Implementa la interfaz de Screen, es decir, se comportara con las caracteristicas de una pantalla
	// sus funciones se llaman automaticamente cuando ocurre el evento al que estan asociadas (renderizar,
	//reescalar, pausar, resumir...) menos con dispose, para liberar los recursos hay que llamar a dispose manualmente
	
	private static final float LIMITE_DISPARO = Gdx.graphics.getHeight(); // La distancia que tiene que recorrer el disparo.
	private static final float MAXIMO_ALIENS_FILA = 8; // Numero maximo de aliens
	private static final float DISTANCIA = 50; // Distancia en el eje x donde se van colocando los aliens.
	private static final float ALTURA = Gdx.graphics.getHeight() - 70; // Distancia en el eje y donde empiezan los aliens.
	private static final float MARGEN_IZQUIERDO = 50; // Limite de los aliens a la izquierda.
	private static final float MARGEN_DERECHO = Gdx.graphics.getWidth(); // Limite de los aliens a la derecha.
	private static final float AUMENTO_DE_VELOCIDAD = 1.5f; // Velocidad que se le van a aumentar a los aliens cuando se pasa cada fase.
	
	private Texture TexturaFondo; // Una Texture es una clase que envuelve una textura estandar de OpenGL, se utiliza para imagenes simples.
	private Texture texturaAlien1, texturaAlien2, texturaAlien3, texturaAlien4; // Textura de los aliens
	private Ship nave; // Nave del juego.
	
	private ArrayList<Alien> aliensTipo1; // Los aliens tipo1 del juego.
	private ArrayList<Alien> aliensTipo2; // Los aliens tipo2 del juego.
	private ArrayList<Alien> aliensTipo3; // Los aliens tipo3 del juego.
	private ArrayList<Alien> aliensTipo4; // Los aliens tipo4 del juego.
	
	private Shot disparoNave; // El disparo que matará los aliens.
	private Shot disparoAlien; // El disparo que lanzarán los aliens para destruir la nave.
	private boolean actualizarDisparoNave; // Actualiza el disparo de la nave.
	private boolean actualizarDisparoAlien; // Actualiza el disparo de los aliens.
	private boolean gameOver; // Te indica si has perdido o no.
	private int marcadorDePuntos; // Sirve para contar los puntos al destruir aliens.
	private float velocidadAliens; // La velocidad con la que se moveran los aliens. Aumentará a medida que se avance en las distintas fases.
	private Random aleatorio; // Permite usar números aleatorios
	private int vidasNave; // Vidas que tendrá nuestra nave.
	
	public GameScreen(spaceInvaders invaders) {
		super(invaders);
	}
	
	@Override
	public void show() {
		//Creamos las texturas, primero el fondo del juego.
		TexturaFondo = invaders.getManager().get("data/Background.png", Texture.class); // Asociamos la textura con la imagen correspondiente
		TexturaFondo.setFilter(TextureFilter.Linear, TextureFilter.Linear); // con setFilter controlamos la forma en la que la imagen se 
		//reescala, le añadimos el parametro TextureFilter.Linear en ambos casos, para que este reescalado sea lineal.
		
		// Creamos la nave
		nave = new Ship(invaders, new Vector2(10,10));  //Creamos la nave en la posición de la panatalla que queremos(10, 10), y ancho y altura de la imagen.
		
		// Creamos los aliens y los introducimos en los ArrayList(Uno por los 4 tipos de alien que hay).
		aliensTipo1 = new ArrayList<Alien>(); // Inicializamos los ArrayList
		aliensTipo2 = new ArrayList<Alien>();
		aliensTipo3 = new ArrayList<Alien>();
		aliensTipo4 = new ArrayList<Alien>();
		
		velocidadAliens = 1.2f; // La velocidad de inicio.
		
		crearAliens(); // Crea los aliens en el juego
		
		aleatorio = new Random();
		gameOver = false;
		marcadorDePuntos = 0; // Los puntos al empezar el juego.
		vidasNave = 3; // Ponemos las vidas que tiene que tener la nave.
	}
	
	private void crearAliens() { // Crea los aliens.
		// Creamos los aliens tipo1
		texturaAlien1 = invaders.getManager().get("data/alien1.png", Texture.class);
		texturaAlien1.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		float altura = ALTURA; // La altura a la que tienen que estar los aliens en la pantalla.
		posicionarAliens(aliensTipo1, texturaAlien1, altura);
		altura = reducirAlturaAliens(texturaAlien1, altura);
				
		// Creamos los aliens tipo2
		texturaAlien2 = invaders.getManager().get("data/alien2.png", Texture.class);
		texturaAlien2.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		posicionarAliens(aliensTipo2, texturaAlien2, altura);
		altura = reducirAlturaAliens(texturaAlien2, altura);
				
		// Creamos los aliens tipo3
		texturaAlien3 = invaders.getManager().get("data/alien3.png", Texture.class);
		texturaAlien3.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		posicionarAliens(aliensTipo3, texturaAlien3, altura);
		altura = reducirAlturaAliens(texturaAlien3, altura);
				
		// Creamos los aliens tipo4
		texturaAlien4 = invaders.getManager().get("data/alien4.png", Texture.class);
		texturaAlien4.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		posicionarAliens(aliensTipo4, texturaAlien4, altura);
		
		actualizarDisparoNave = false;
		actualizarDisparoAlien = false;
	}
	
	private void posicionarAliens(ArrayList<Alien> aliens, Texture texturaAlien, float altura) { // Método que sirve para ubicar los aliens en la pantalla
		float distancia = DISTANCIA; // Distancia a la que tiene que estar cada alien en la pantalla.
		float limiteDerecha = MARGEN_DERECHO - ((texturaAlien.getWidth() + 5) * MAXIMO_ALIENS_FILA); // Sirve para establecer el limite de cada alien por la derecha.
		float limiteIzquierda = MARGEN_IZQUIERDO; // Sirve para establecer el limite de cada alien por la izquierda.
		for(int i = 0; i < MAXIMO_ALIENS_FILA ; i++) {
			aliens.add(new Alien(texturaAlien, new Vector2(distancia, altura), limiteIzquierda, limiteDerecha, velocidadAliens));
			distancia = distancia + texturaAlien.getHeight() + 5;
			limiteDerecha = limiteDerecha + texturaAlien.getHeight() + 5;  // Actalizamos los 3 valores para crear el siguiente alien.
			limiteIzquierda = limiteIzquierda + texturaAlien.getHeight() + 5;
		}
	}
	
	private float reducirAlturaAliens(Texture texturaAlien, float altura) { // Descender la altura de la nueva fila de aliens
		return altura - texturaAlien.getHeight() - 5;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1); //Gdx es una clase con la que podemos acceder a variables que hacen referencia a todos los subsitemas, como son graficos, audio, ficheros, entrada y aplicaciones
		// gl es una variable de tipo GL, nos permite acceder a metodos de GL10, GL11 y GL20
		//En este caso glClearColor es un bucle (game loop) que establecera el fondo de la pantalla negro (0,0,0) con transparencia 1
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT); // Despues de la funcion anterior es necesario ejecutar esta, para que se lleve a cabo
		
		volverAlMenu(); // Método que permite volver a atras al menú principal.
		
		//Hacemos que se actualizen los parametros de la nave en la pantalla.
		boolean naveDispara = nave.update(); // Devuelve true si se ha disparado.
		
		// Se actualizan todos los valores de los distintos tipos de disparos.
		disparoNaveUpdate(naveDispara);
		disparoAlienUpdate();
		
		//Hacemos que se actualizen los parametros de los aliens de cada tipo
		aliensUpdate(aliensTipo1);
		aliensUpdate(aliensTipo2);
		aliensUpdate(aliensTipo3);
		aliensUpdate(aliensTipo4);
		
		batch.begin(); // Aqui por fin comenzamos el renderizado
		//Dibujamos el fondo
		batch.draw(TexturaFondo, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); //La dibujamos en la esquina inferior derecha, tamaño natural
		//Dibujamos nuestra nave
		nave.draw(batch);
		
		//Dibujamos los aliens
		pintarAliens(aliensTipo1, texturaAlien1);
		pintarAliens(aliensTipo2, texturaAlien2);
		pintarAliens(aliensTipo3, texturaAlien3);
		pintarAliens(aliensTipo4, texturaAlien4);
		
		//Dibujamos los disparo si hay alguno.
		if(actualizarDisparoNave)
			disparoNave.draw(batch);
		if(actualizarDisparoAlien)
			disparoAlien.draw(batch);
		
		// Pinta los puntos que se tienen en la pantalla.
		invaders.getFont().draw(batch, Integer.toString(marcadorDePuntos), 10, Gdx.graphics.getHeight() - 10);
		
		//Pintamos las vidas de la nave.
		for(int i = 0; i < vidasNave; i++)
			batch.draw(invaders.getManager().get("data/ship.png", Texture.class), Gdx.graphics.getWidth() - 40 - i*35, Gdx.graphics.getHeight() - 40, 30, 30);
		
		batch.end(); // Terminamos el renderizado.
		
		comprobarFinalDelJuego();
	}
	
	private void disparoNaveUpdate(boolean naveDispara) { // Método que actualiza el disparo de la nave.
		// Creamos el disparo si se ha realizado
		if(naveDispara && !actualizarDisparoNave) {
			disparoNave = new ShotShip(invaders, new Vector2(nave.getPosicion().x + (nave.getAnchura() / 2 - 1), nave.getAltura() + 12));
			disparoNave.disparoSonido(); // Sonido del disparo.
			actualizarDisparoNave = true; // Esta variable sirve para no hacer un disparo hasta que se termine el actual y para actualizar los parametros del mismo.
		}
		//Hacemos que se actualizen los parametros del disparo en la pantalla si hay efectuado uno.
		if(actualizarDisparoNave) {
			disparoNave.update();
			if(disparoNave.getPosicion().y + disparoNave.getAltura() > LIMITE_DISPARO) { // Cuando llegue al final de la pantalla
				actualizarDisparoNave = false;
			}
		}
	}
	private void disparoAlienUpdate() { // Método que actualiza el disparo de los aliens.
		// Creamos el disparo si se ha realizado
		if(!actualizarDisparoAlien) {
			ArrayList<Alien> filaDisparo = filaDeDisparo();
			int alienAleatorio = aleatorio.nextInt(filaDisparo.size());
			disparoAlien = new ShotAlien(invaders, new Vector2(filaDisparo.get(alienAleatorio).getPosicion().x + (filaDisparo.get(alienAleatorio).getAnchura() / 2 - 1), filaDisparo.get(alienAleatorio).getPosicion().y - 5));
			disparoAlien.disparoSonido(); // Sonido del disparo.
			actualizarDisparoAlien = true; // Esta variable sirve para no hacer un disparo hasta que se termine el actual y para actualizar los parametros del mismo.
		}
		//Hacemos que se actualizen los parametros del disparo en la pantalla si hay efectuado uno.
		if(actualizarDisparoAlien) {
			disparoAlien.update();
			if(nave.tocadoPorDisparo(disparoAlien)) { // Si el disparo toca la nave, pues pierde una vida
				disparoAlien.naveTocada();
				nave.volverAPosicionInicial();
				vidasNave--; // Disminuimos las vidas
				if(vidasNave == 0) // Si las vidas se quedan a 0
					gameOver = true; // Perdemos
			}
			else if(disparoAlien.getPosicion().y < 0) { // Cuando llegue al final de la pantalla
				actualizarDisparoAlien = false;
			}
		}
	}
	
	private ArrayList<Alien> filaDeDisparo() { // Permite comprobar la fila mas en el sur de la pantalla para disparar. Dispara por fila siempre y cuando existan aliens en la misma
		if(aliensTipo4.isEmpty()) {
			if(aliensTipo3.isEmpty()) {
				if(aliensTipo2.isEmpty()) {
					return aliensTipo1;
				}
				else return aliensTipo2;
			}
			else return aliensTipo3;
		}
		else return aliensTipo4;
	}
	
	private void aliensUpdate(ArrayList<Alien> aliens) { // Esta función permite controlar los eventos que puedan pasarles a los aliens
		for(int i = 0; i < aliens.size(); i++) {
			aliens.get(i).update();
			comprobarDerrota(aliens.get(i).getBordes().y); // Comprobamos si los aliens han llegado a la nave para indicar el "GameOver".
			if(actualizarDisparoNave && aliens.get(i).Muerto(disparoNave)) { // Si hay un disparo efectuado y le ha dado al alien
				marcadorDePuntos += 10; // Se aumenta en 10 la puntuación cuando se destruye un alien.
				aliens.remove(i); // Se destruye el alien!!
				disparoNave.alienMuerto(); // Permite quitar el disparo de la pantalla y hace el sonido de explosion
			}
		}
	}
	
	private void pintarAliens(ArrayList<Alien> aliens, Texture texturaAlien) { // Pinta los aliens en la pantalla por cada lista
		for(Alien alien : aliens) {
			alien.draw(batch);
		}
	}
	
	private void volverAlMenu() { // Método privado que tiene los botones para volver al menú durante el juego
		if(Gdx.input.isKeyPressed(Keys.ESCAPE) || Gdx.input.isKeyPressed(Keys.BACK) || Gdx.input.isKeyPressed(Keys.MENU))
			invaders.setScreen(invaders.PRINCIPAL);
	}
	
	private boolean estanTodosLosAliensDestruidos() {
		// Si se han destruido todo los aliens.
		return (aliensTipo1.isEmpty() && aliensTipo2.isEmpty() && aliensTipo3.isEmpty() && aliensTipo4.isEmpty()); 
	}
	
	private void comprobarFinalDelJuego() { // Comprueba si se ha acabado el juego
		if(estanTodosLosAliensDestruidos()) { // Ganaste!!
			velocidadAliens *= AUMENTO_DE_VELOCIDAD; // Si se destruyen todos los aliens, se aumenta la velocidad.
			crearAliens(); // Crear de nuevo con mas velocidad y se sigue jugando.
		}
		else if(gameOver) { // Perdiste...
			guardarRecord();
			invaders.setScreen(invaders.GAMEOVER);
		}
	}
	
	private void guardarRecord() { // Esta función permite almacenar en el dispositivo la puntuación siempre que sea superior a las 3 mejores
		if(marcadorDePuntos > invaders.getPreferencias().getInteger("primerRecord", 0)) { // Si es la mayor puntuación obtenida en el juego.
			invaders.getPreferencias().putInteger("tercerRecord", invaders.getPreferencias().getInteger("segundoRecord", 0)); // Desplazamos los records a una posición más baja.
			invaders.getPreferencias().putInteger("segundoRecord", invaders.getPreferencias().getInteger("primerRecord", 0)); // El segundo al tercero, y el primero al segundo.
			invaders.getPreferencias().putInteger("primerRecord", marcadorDePuntos); // Introducimos en el primer record el mejor obtenido.
		}
		else if(marcadorDePuntos > invaders.getPreferencias().getInteger("segundoRecord", 0)) {
			invaders.getPreferencias().putInteger("tercerRecord", invaders.getPreferencias().getInteger("segundoRecord", 0));
			invaders.getPreferencias().putInteger("segundoRecord", marcadorDePuntos);
		}
		else if(marcadorDePuntos > invaders.getPreferencias().getInteger("tercerRecord", 0))
			invaders.getPreferencias().putInteger("tercerRecord", marcadorDePuntos);
		invaders.getPreferencias().flush();
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
		// TODO Auto-generated method stub
	}
}
