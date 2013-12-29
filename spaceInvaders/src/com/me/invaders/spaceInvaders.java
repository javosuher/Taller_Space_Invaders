package com.me.invaders;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.invaders.screens.AbstractScreen;
import com.me.invaders.screens.GameOverScreen;
import com.me.invaders.screens.GameScreen;
import com.me.invaders.screens.LoadingScreen;
import com.me.invaders.screens.WinScreen;

/*AplicationListener es una interfaz que proporciona metodos que se llaman cada vez que es necesario
 * crear, pausar, continuar, renderizar o destruir una aplicacion, nos permite ademas manejar los graficos
 */

/* Game es una clase que implementa de AplicationListener y que permite delegar en una Screen, 
 * es decir, que permite a la alicacion tener y manejar facilmente varias ventanas
 */

public class spaceInvaders extends Game {
	public AbstractScreen PRINCIPAL, GAMEOVER, WIN, LOADING; // Las pantallas que va a tener el juego
	private AssetManager manager; // Permite cargar los recursos.
	private SpriteBatch batch; // "Grupo de Sprites (imagenes)" nos permite dibujar rectagulos como referencias a texturas, es necesario para mostrar todo por pantalla

	@Override
	public void create() {
		manager = new AssetManager();
		batch = new SpriteBatch(); // Es recomendable solo usar un SpriteBatch en todo el juego
		
		// Pantallas del juego
		PRINCIPAL = new GameScreen(this);
		GAMEOVER = new GameOverScreen(this);
		WIN = new WinScreen(this);
		LOADING = new LoadingScreen(this);
		
		// Cargamos todos los elementos externos que usará el juego, como son las texturas y los sonidos.
		manager.load("data/Loading.png", Texture.class);
		manager.load("data/Background.png", Texture.class);
		manager.load("data/GameOver.png", Texture.class);
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

	public SpriteBatch getBatch() {
		return batch;
	}
	
	public AssetManager getManager() {
		return manager;
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
