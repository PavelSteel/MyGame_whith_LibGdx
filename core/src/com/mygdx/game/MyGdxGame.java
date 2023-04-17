package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MyGdxGame extends Game {
    private SpriteBatch batch;
    private GameScreen gameScreen;
    private Viewport viewport;

    public Viewport getViewport() {
        return viewport;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        gameScreen = new GameScreen(this, batch);
        viewport = new FitViewport(1280, 720);
        setScreen(gameScreen);
    }

    @Override
    public void render() {
        float dt = Gdx.graphics.getDeltaTime();
        getScreen().render(dt);
    }


    @Override
    public void dispose() {
        batch.dispose();
        getScreen().dispose();
    }
}
