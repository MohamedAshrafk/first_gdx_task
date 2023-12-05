package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;

public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;
    private static final int GENERAL_HEIGHT_SPACING = 100;


    private Stage stage;
    private Skin skin;

    BitmapFont font;

    @Override
    public void create() {
        float START_HEIGHT = Gdx.graphics.getHeight() * 0.9f;
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("default.fnt"));

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        Label.LabelStyle labelStyle = new Label.LabelStyle(skin.getFont("default-font"), skin.getColor("white"));
        labelStyle.font.getData().setScale(3f); // Change the font size (adjust the scale factor)

        Label userNameLabel = new Label("User Name", labelStyle);
        Label emailLabel = new Label("Email", labelStyle);
        Label passwordLabel = new Label("Password", labelStyle);
        userNameLabel.setAlignment(Align.left);
        emailLabel.setAlignment(Align.left);
        passwordLabel.setAlignment(Align.left);
//        userNameLabel.setFontScale(3f);

        userNameLabel.setPosition(Gdx.graphics.getWidth() / 6f - userNameLabel.getWidth() / 2f, START_HEIGHT);
        emailLabel.setPosition(Gdx.graphics.getWidth() / 6f - userNameLabel.getWidth() / 2f, START_HEIGHT - GENERAL_HEIGHT_SPACING);
        passwordLabel.setPosition(Gdx.graphics.getWidth() / 6f - userNameLabel.getWidth() / 2f, START_HEIGHT - GENERAL_HEIGHT_SPACING * 2);

        stage.addActor(userNameLabel);
        stage.addActor(emailLabel);
        stage.addActor(passwordLabel);
    }

    @Override
    public void render() {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
    }


    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
