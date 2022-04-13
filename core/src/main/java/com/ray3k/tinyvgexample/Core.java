package com.ray3k.tinyvgexample;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import dev.lyze.gdxtinyvg.TinyVG;
import dev.lyze.gdxtinyvg.TinyVGAssetLoader;
import dev.lyze.gdxtinyvg.drawers.TinyVGShapeDrawer;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Core extends ApplicationAdapter {
	private TinyVGShapeDrawer drawer;
	private Viewport viewport = new ScreenViewport();
	private Animation<TinyVG> animation;
	private float time;

	@Override
	public void create() {
		TinyVGAssetLoader assetLoader = new TinyVGAssetLoader();
		
		drawer = new TinyVGShapeDrawer(new SpriteBatch());
		Array<TinyVG> frames = new Array<>();
		for (int i = 1; i <= 22; i++) {
			frames.add(assetLoader.load("explosion (" + i + ").tvg"));
		}
		
		animation = new Animation<>(.05f, frames);
		animation.setPlayMode(PlayMode.LOOP);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0.25f, 0.25f, 0.25f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		viewport.apply();
		drawer.getBatch().setProjectionMatrix(viewport.getCamera().combined);
		
		time += Gdx.graphics.getDeltaTime();
		
		drawer.getBatch().begin();
		animation.getKeyFrame(time).draw(drawer);
		drawer.getBatch().end();
	}
	
	@Override
	public void resize(int width, int height) {
		viewport.update(width, height, true);
	}
	
	@Override
	public void dispose() {

	}
}