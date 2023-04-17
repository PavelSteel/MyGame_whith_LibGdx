package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen {
    private MyGdxGame runnerGame;
    private SpriteBatch batch;
    private Texture textureBackground;
    private Texture textureSand;

    private float worldX;
    private float groundHeight = 190.0f;
    private float playerAncor = 200.0f;

    private Player player;

    public float getPlayerAncor() {
        return playerAncor;
    }

    public GameScreen(MyGdxGame runnerGame, SpriteBatch batch) {
        this.runnerGame = runnerGame;
        this.batch = batch;
    }

    @Override
    public void show() {
        textureBackground = new Texture("bg.png");
        textureSand = new Texture("ground.png");
        player = new Player(this);
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(textureBackground, 0, 0);
        for (int i = 0; i < 8; i++) {
            batch.draw(textureSand, i * 200 - worldX % 200, 0);
        }
        player.render(batch);
        batch.end();
    }

    public void update(float dt){
        player.update(dt);
        worldX += 200.0f * dt;
    }

    @Override
    public void resize(int width, int height) {
        runnerGame.getViewport().update(width, height, true);
        runnerGame.getViewport().apply();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        textureSand.dispose();
        textureBackground.dispose();
    }
}
