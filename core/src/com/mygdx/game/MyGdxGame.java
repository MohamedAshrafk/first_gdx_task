package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;

public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;
    private static final int GENERAL_HEIGHT_SPACING = 100;
    private static final int GENERAL_WIDTH_SPACING = 250;
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

        Table table = new Table();
        table.setFillParent(true);
        table.align(Align.topLeft);
        table.padLeft(10);


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


        TextField userNameTF = new TextField("", skin);
        userNameTF.setWidth(TEXT_FIELD_WIDTH);

        TextField emailTF = new TextField("", skin);
        emailTF.setWidth(TEXT_FIELD_WIDTH);

        TextField passwordTF = new TextField("", skin);
        passwordTF.setWidth(TEXT_FIELD_WIDTH);

        // Create a CheckBox with a label
        CheckBox checkBoxMale = new CheckBox("Male", skin);
        checkBoxMale.setScale(10f);
        CheckBox checkBoxFemale = new CheckBox("Female", skin);
        checkBoxFemale.setScale(10f);

        checkBoxMale.getImage().setScaling(Scaling.fill);
        checkBoxMale.getImageCell().size(50);
        checkBoxFemale.getImage().setScaling(Scaling.fill);
        checkBoxFemale.getImageCell().size(50);

        table.add(userNameLabel).padRight(GENERAL_WIDTH_SPACING).align(Align.left);
        table.add(userNameTF).colspan(2).width(TEXT_FIELD_WIDTH).row();
        table.add().padTop(GENERAL_HEIGHT_SPACING).row();

        table.add(emailLabel).padRight(GENERAL_WIDTH_SPACING).align(Align.left);
        table.add(emailTF).colspan(2).width(TEXT_FIELD_WIDTH).row();
        table.add().padTop(GENERAL_HEIGHT_SPACING).row();


        table.add(passwordLabel).padRight(GENERAL_WIDTH_SPACING).align(Align.left);
        table.add(passwordTF).colspan(2).width(TEXT_FIELD_WIDTH).row();
        table.add().padTop(GENERAL_HEIGHT_SPACING).row();

        table.add(genderLabel).padRight(GENERAL_WIDTH_SPACING).align(Align.left);
        table.add(checkBoxMale).align(Align.left);
        table.add(checkBoxFemale).align(Align.left).row();
        table.row();
        table.add().padTop(GENERAL_HEIGHT_SPACING).row();

        table.add(cityLabel).padRight(GENERAL_WIDTH_SPACING).align(Align.left);
        table.row();
        table.add().padTop(GENERAL_HEIGHT_SPACING).row();

        table.add(tallLabel).padRight(GENERAL_WIDTH_SPACING).align(Align.left);
        table.row();
        table.add().padTop(GENERAL_HEIGHT_SPACING).row();

        table.add(countryLabel).padRight(GENERAL_WIDTH_SPACING).align(Align.left);
        table.row();
        table.add().padTop(GENERAL_HEIGHT_SPACING).row();

        table.add(degreeLabel).padRight(GENERAL_WIDTH_SPACING).align(Align.left);
        table.row();
        table.add().padTop(GENERAL_HEIGHT_SPACING).row();


        // adding everything to the stage
        stage.addActor(table);
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
