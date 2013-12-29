package com.me.invaders;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Space Invaders";
		cfg.useGL20 = true;
		cfg.width = 946;
		cfg.height = 600;
		
		new LwjglApplication(new spaceInvaders(), cfg);
	}
}
