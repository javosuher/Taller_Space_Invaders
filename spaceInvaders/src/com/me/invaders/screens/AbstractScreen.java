package com.me.invaders.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.invaders.spaceInvaders;

public abstract class AbstractScreen implements Screen {
	protected spaceInvaders invaders;
	protected SpriteBatch batch;

	public AbstractScreen(spaceInvaders invaders) {
		this.invaders = invaders;
		this.batch = invaders.getBatch();
	}
	
	public spaceInvaders getInvaders() {
		return invaders;
	}
	public SpriteBatch getBatch() {
		return batch;
	}

	@Override
	public void resize(int width, int height) {
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
}
