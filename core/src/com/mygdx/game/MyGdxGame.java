package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;

import com.badlogic.gdx.graphics.GL20;

public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;

    private Stage stage;
    private Skin skin;

    BitmapFont font;

    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        font = new BitmapFont(Gdx.files.internal("default.fnt"));

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("uiskin.json"));

        Label.LabelStyle labelStyle = new Label.LabelStyle(skin.getFont("default-font"), skin.getColor("white"));
        labelStyle.font.getData().setScale(4.0f); // Change the font size (adjust the scale factor)


        Label userNameLabel = new Label("User Name", labelStyle);
        userNameLabel.setAlignment(Align.left);
//        userNameLabel.setFontScale(3f);

        userNameLabel.setPosition(Gdx.graphics.getWidth() / 6f - userNameLabel.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);

        stage.addActor(userNameLabel);
    }

    @Override
    public void render() {
//		ScreenUtils.clear(0, 0, 0, 1);
//		batch.begin();

//		font.getData().setScale(3f);
//
//		GlyphLayout userNameLayout = new GlyphLayout(font, "User Name", Color.WHITE, 0, Align.left, false);
//		GlyphLayout emailLayout = new GlyphLayout(font, "Email", Color.WHITE, 0, Align.left, false);
//		GlyphLayout passwordLayout = new GlyphLayout(font, "Password", Color.WHITE, 0, Align.left, false);
//
//
//		font.draw(batch, userNameLayout, 100, 2000);
//		font.draw(batch, emailLayout, 100, 1900);
//		font.draw(batch, passwordLayout, 100, 1800);
////		batch.draw(img, 0, 0);


//		batch.end();

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
