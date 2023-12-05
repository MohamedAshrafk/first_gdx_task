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
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.utils.Align;

public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;
    private static final int GENERAL_HEIGHT_SPACING = 150;
    private static final int GENERAL_WIDTH_SPACING = 240;
    private static final int TEXT_FIELD_WIDTH = 500;


    private Stage stage;

    BitmapFont font;

    @Override
    public void create() {
        float START_HEIGHT = Gdx.graphics.getHeight() * 0.9f;
        float START_WIDTH = Gdx.graphics.getWidth() * 0.15f;


        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("default.fnt"));

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));


        // Labels styles and settings
        Label.LabelStyle labelStyle = new Label.LabelStyle(skin.getFont("default-font"), skin.getColor("white"));
        labelStyle.font.getData().setScale(3f); // Change the font size (adjust the scale factor)

        Label userNameLabel = new Label("User Name", labelStyle);
        Label emailLabel = new Label("Email", labelStyle);
        Label passwordLabel = new Label("Password", labelStyle);
        Label genderLabel = new Label("Gender", labelStyle);
        Label cityLabel = new Label("City", labelStyle);
        Label tallLabel = new Label("Tall", labelStyle);
        Label countryLabel = new Label("Country", labelStyle);
        Label degreeLabel = new Label("Degree", labelStyle);
        Label activeLabel = new Label("Active", labelStyle);
        userNameLabel.setAlignment(Align.left);
        emailLabel.setAlignment(Align.left);
        passwordLabel.setAlignment(Align.left);
        genderLabel.setAlignment(Align.left);
        cityLabel.setAlignment(Align.left);
        tallLabel.setAlignment(Align.left);
        countryLabel.setAlignment(Align.left);
        degreeLabel.setAlignment(Align.left);
        activeLabel.setAlignment(Align.left);

        TextField userNameTF = new TextField("", skin);
        userNameTF.setWidth(TEXT_FIELD_WIDTH);

        TextField emailTF = new TextField("", skin);
        emailTF.setWidth(TEXT_FIELD_WIDTH);

        TextField passwordTF = new TextField("", skin);
        passwordTF.setWidth(TEXT_FIELD_WIDTH);

        // set the positions for all views
        userNameLabel.setPosition(START_WIDTH - userNameLabel.getWidth() / 2f, START_HEIGHT);
        userNameTF.setPosition(START_WIDTH - userNameLabel.getWidth() / 2 + userNameLabel.getWidth() + GENERAL_WIDTH_SPACING,
                START_HEIGHT);

        emailLabel.setPosition(START_WIDTH - userNameLabel.getWidth() / 2f, START_HEIGHT - GENERAL_HEIGHT_SPACING);
        emailTF.setPosition(START_WIDTH - userNameLabel.getWidth() / 2f + userNameLabel.getWidth() + GENERAL_WIDTH_SPACING,
                START_HEIGHT - GENERAL_HEIGHT_SPACING);

        passwordLabel.setPosition(START_WIDTH - userNameLabel.getWidth() / 2f, START_HEIGHT - GENERAL_HEIGHT_SPACING * 2);
        passwordTF.setPosition(START_WIDTH - userNameLabel.getWidth() / 2f + userNameLabel.getWidth() + GENERAL_WIDTH_SPACING,
                START_HEIGHT - GENERAL_HEIGHT_SPACING * 2);

        genderLabel.setPosition(START_WIDTH - userNameLabel.getWidth() / 2f, START_HEIGHT - GENERAL_HEIGHT_SPACING * 3);
        cityLabel.setPosition(START_WIDTH - userNameLabel.getWidth() / 2f, START_HEIGHT - GENERAL_HEIGHT_SPACING * 4);
        tallLabel.setPosition(START_WIDTH - userNameLabel.getWidth() / 2f, START_HEIGHT - GENERAL_HEIGHT_SPACING * 5);
        countryLabel.setPosition(START_WIDTH - userNameLabel.getWidth() / 2f, START_HEIGHT - GENERAL_HEIGHT_SPACING * 6);
        degreeLabel.setPosition(START_WIDTH - userNameLabel.getWidth() / 2f, START_HEIGHT - GENERAL_HEIGHT_SPACING * 7);
        activeLabel.setPosition(START_WIDTH - userNameLabel.getWidth() / 2f, START_HEIGHT - GENERAL_HEIGHT_SPACING * 8);


        // adding everything to the stage
        stage.addActor(userNameLabel);
        stage.addActor(emailLabel);
        stage.addActor(passwordLabel);
        stage.addActor(genderLabel);
        stage.addActor(cityLabel);
        stage.addActor(tallLabel);
        stage.addActor(countryLabel);
        stage.addActor(degreeLabel);
        stage.addActor(activeLabel);

        stage.addActor(userNameTF);
        stage.addActor(emailTF);
        stage.addActor(passwordTF);
    }

    @Override
    public void render() {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update and draw the stage
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }


    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
