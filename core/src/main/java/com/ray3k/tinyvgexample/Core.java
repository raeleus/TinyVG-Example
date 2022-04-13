package com.ray3k.tinyvgexample;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import dev.lyze.gdxtinyvg.TinyVG;
import dev.lyze.gdxtinyvg.TinyVGAssetLoader;
import dev.lyze.gdxtinyvg.drawers.TinyVGShapeDrawer;
import dev.lyze.gdxtinyvg.scene2d.TinyVGDrawable;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Core extends ApplicationAdapter {
	private TinyVG tvg;
	private TinyVGShapeDrawer drawer;
	private Viewport viewport;
	private SpriteBatch spriteBatch;
	private Stage stage;

	@Override
	public void create() {
		spriteBatch = new SpriteBatch();
		viewport = new ScreenViewport();
		drawer = new TinyVGShapeDrawer(spriteBatch);
		stage = new Stage(viewport, spriteBatch);
		
		Gdx.input.setInputProcessor(stage);
		Table root = new Table();
		root.setFillParent(true);
		stage.addActor(root);
		
		TinyVGAssetLoader assetLoader = new TinyVGAssetLoader();
		tvg = assetLoader.load("pig.tvg");
		TinyVGDrawable drawable = new TinyVGDrawable(tvg, drawer);
		
		Table table = new Table();
		table.setBackground(drawable);
		root.add(table).size(300);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0.25f, 0.25f, 0.25f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act();
		stage.draw();
	}
	
	@Override
	public void resize(int width, int height) {
		viewport.update(width, height, true);
	}
	
	@Override
	public void dispose() {

	}
}