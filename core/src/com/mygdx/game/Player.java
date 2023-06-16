package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player {
    private GameScreen gameScreen;

    private Texture texture;
    private Vector2 position;
    private Vector2 velocity;
    private Sound jumpSound;

    private Rectangle rectangle;

    private float score;

    private float time;

    private final int WIDTH = 100;
    private final int HEIGHT = 100;

    public Vector2 getPosition() {
        return position;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public float getScore() {
        return score;
    }

    public Player(GameScreen gameScreen, Sound jumpSound) {
        this.gameScreen = gameScreen;
        this.texture = new Texture("runner2.png");
        this.position = new Vector2(0, 190);
        this.velocity = new Vector2(240.0f, 0.0f);
        this.rectangle = new Rectangle(position.x + WIDTH / 4, position.y, WIDTH / 4, HEIGHT);
        this.score = 0;
        this.jumpSound = jumpSound;
    }

    public void render(SpriteBatch spriteBatch) {
        int frame = (int) (time / 0.1f);
        frame = frame % 6;
        spriteBatch.draw(texture, gameScreen.getPlayerAncor(), position.y, frame * 100, 0, WIDTH, HEIGHT);
    }

    public void restart(){
        position.set(0, gameScreen.getGroundHeight());
        score = 0;
        velocity.set(240.0f, 0.0f);
        rectangle.setPosition(position);
    }

    public void update(float dt) {
        if (position.y > gameScreen.getGroundHeight()) {
            velocity.y -= 720.0f * dt;
        } else {
            position.y = gameScreen.getGroundHeight();
            velocity.y = 0.0f;
            time += velocity.x * dt / 300.f;
            if (Gdx.input.justTouched()) {
                velocity.y = 420.0f;
                jumpSound.play();
            }
        }
        position.mulAdd(velocity, dt);
        velocity.x += 5.0f * dt;
        score += velocity.x * dt / 5.0f;
        rectangle.setPosition(position.x + WIDTH / 4, position.y);
    }
}
