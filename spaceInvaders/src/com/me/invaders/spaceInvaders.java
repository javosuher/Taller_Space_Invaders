package com.me.invaders;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.invaders.screens.AbstractScreen;
import com.me.invaders.screens.GameOverScreen;
import com.me.invaders.screens.GameScreen;
import com.me.invaders.screens.LoadingScreen;
import com.me.invaders.screens.MainScreen;
import com.me.invaders.screens.RecordScreen;
import com.me.invaders.screens.WinScreen;

/*AplicationListener es una interfaz que proporciona metodos que se llaman cada vez que es necesario
 * crear, pausar, continuar, renderizar o destruir una aplicacion, nos permite ademas manejar los graficos
 */

/* Game es una clase que implementa de AplicationListener y que permite delegar en una Screen, 
 * es decir, que permite a la alicacion tener y manejar facilmente varias ventanas
 */

public class spaceInvaders extends Game {
	public AbstractScreen PRINCIPAL, GAMEOVER, WIN, LOADING, JUEGO, RECORDS; // Las pantallas que va a tener el juego
	private AssetManager manager; // Permite cargar los recursos.
	private SpriteBatch batch; // "Grupo de Sprites (imagenes)" nos permite dibujar rectagulos como referencias a texturas, es necesario para mostrar todo por pantalla
	private BitmapFont font; // Sirve para mostrar los letras y números por la pantalla.
	private int marcadorDePuntos; // Sirve para contar los puntos al destruir aliens.
	private Preferences preferencias; // Nos permite almacenar datos en el dispositivo.

	@Override
	public void create() {
		manager = new AssetManager();
		batch = new SpriteBatch(); // Es recomendable solo usar un SpriteBatch en todo el juego
		this.font = new BitmapFont(Gdx.files.internal("data/arial.fnt"), Gdx.files.internal("data/arial.png"), false);
		this.marcadorDePuntos = 0; // Los puntos al empezar el juego.
		
		preferencias = Gdx.app.getPreferences("-_PreferencesInvaders-Data-_"); // Obtenemos los datos del fichero, o si no está creado, se crea automaticamente
		
		// Pantallas de carga del juego
		LOADING = new LoadingScreen(this);
		
		// Cargamos todos los elementos externos que usará el juego, como son las texturas y los sonidos.
		manager.load("data/Loading.png", Texture.class);
		manager.load("data/Background.png", Texture.class);
		manager.load("data/GameOver.png", Texture.class);
		manager.load("data/Records.png", Texture.class);
		manager.load("data/BotonPlay.png", Texture.class);
		manager.load("data/BotonExit.png", Texture.class);
		manager.load("data/BotonRecords.png", Texture.class);
		manager.load("data/Win.png", Texture.class);
		manager.load("data/alien1.png", Texture.class);
		manager.load("data/alien2.png", Texture.class);
		manager.load("data/alien3.png", Texture.class);
		manager.load("data/alien4.png", Texture.class);
		manager.load("data/ship.png", Texture.class);
		manager.load("data/shot.png", Texture.class);
		manager.load("data/explosion.wav", Sound.class);
		manager.load("data/shot.wav", Sound.class);
		
		// Coloca la pantalla actual, se llama desde cualquier pantalla anterior y se llama a Screen.show desde la nueva pantalla
		setScreen(LOADING);
	}
	
	public void cargarPantallas() {
		// Se cargan todas las demás pantallas del juego
		PRINCIPAL = new MainScreen(this);
		JUEGO = new GameScreen(this);
		GAMEOVER = new GameOverScreen(this);
		WIN = new WinScreen(this);
		RECORDS = new RecordScreen(this);
	}

	public SpriteBatch getBatch() {
		return batch;
	}
	
	public AssetManager getManager() {
		return manager;
	}
	
	public BitmapFont getFont() {
		return font;
	}
	
	public Preferences getPreferencias() {
		return preferencias;
	}

	public int getMarcadorDePuntos() {
		return marcadorDePuntos;
	}

	public void setMarcadorDePuntos(int marcadorDePuntos) {
		this.marcadorDePuntos = marcadorDePuntos;
	}

	@Override
	public void dispose() {
		super.dispose();
		manager.dispose();
		batch.dispose();
	}
	
	@Override
	public void render() {		
		super.render();
	}
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}
	@Override
	public void pause() {
		super.pause();
	}
	@Override
	public void resume() {
		super.resume();
	}
}
