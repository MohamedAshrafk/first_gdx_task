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
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;

public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;
    private static final int GENERAL_HEIGHT_SPACING = 50;
    private static final int GENERAL_WIDTH_SPACING = 250;
    private static final int TEXT_FIELD_WIDTH = 500;
    private static final int TABLE_HORIZONTAL_PADDING = 30;
    private static final int TABLE_VERTICAL_PADDING = 50;
    private static final int BIG_TEXT_FIELD_HEIGHT = 220;


    private Stage stage;

    BitmapFont font;

    @Override
    public void create() {

        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("default.fnt"));

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

        Table table = new Table();
        table.setFillParent(true);
        table.align(Align.topLeft);
        table.padTop(TABLE_VERTICAL_PADDING);
        table.padLeft(TABLE_HORIZONTAL_PADDING);
        table.padRight(TABLE_HORIZONTAL_PADDING);


        // Labels styles and settings
        LabelStyle labelStyle = new LabelStyle(skin.getFont("default-font"), skin.getColor("white"));
        labelStyle.font.getData().setScale(3f); // Change the font size (adjust the scale factor)

        Label userNameLabel = new Label("User Name", labelStyle);
        Label emailLabel = new Label("Email", labelStyle);
        Label passwordLabel = new Label("Password", labelStyle);
        Label genderLabel = new Label("Gender", labelStyle);
        Label cityLabel = new Label("City", labelStyle);
        Label tallLabel = new Label("Tall", labelStyle);
        Label countryLabel = new Label("Country", labelStyle);
        Label degreeLabel = new Label("Degree", labelStyle);
        Label signUpLabel = new Label("Sign Up", labelStyle);


        TextField userNameTF = new TextField("", skin);
        userNameTF.setWidth(TEXT_FIELD_WIDTH);

        TextField emailTF = new TextField("", skin);
        emailTF.setWidth(TEXT_FIELD_WIDTH);

        TextField passwordTF = new TextField("", skin);
        passwordTF.setWidth(TEXT_FIELD_WIDTH);

        TextField cityTF = new TextField("City 1", skin);
        cityTF.setWidth(TEXT_FIELD_WIDTH);

        TextField commentTF = new TextArea("", skin);
        commentTF.setWidth(TEXT_FIELD_WIDTH);

        // Create a CheckBox with a label
        CheckBox maleCB = new CheckBox("Male", skin);
        CheckBox femaleCB = new CheckBox("Female", skin);
        CheckBox activeCB = new CheckBox("Active", skin);

        // making the image assets bigger for the CheckBox widget
        maleCB.getImage().setScaling(Scaling.fill);
        maleCB.getImageCell().size(50);
        femaleCB.getImage().setScaling(Scaling.fill);
        femaleCB.getImageCell().size(50);
        activeCB.getImage().setScaling(Scaling.fill);
        activeCB.getImageCell().size(50);

        // Create a SliderStyle (optional)
        Slider.SliderStyle sliderStyle = new Slider.SliderStyle();
        sliderStyle.background = skin.getDrawable("default-slider"); // Set the background drawable
        sliderStyle.knob = skin.getDrawable("default-slider-knob"); // Set the knob drawable
        sliderStyle.knob.setMinWidth(50);
        sliderStyle.knob.setMinHeight(50);
        sliderStyle.background.setMinHeight(10);

        // Create a Slider with a range and the defined style
        Slider slider = new Slider(0, 100, 1, false, sliderStyle);

        TextButton clearTB = new TextButton("Clear", skin);
        TextButton signUpTB = new TextButton("Sign Up", skin);

        table.add(signUpLabel).colspan(3).align(Align.center).row();
        table.add().padTop(GENERAL_HEIGHT_SPACING).row();

        table.add(userNameLabel).padRight(GENERAL_WIDTH_SPACING).align(Align.left);
        table.add(userNameTF).colspan(2).width(TEXT_FIELD_WIDTH).row();
        table.add().padTop(GENERAL_HEIGHT_SPACING).row();

        table.add(emailLabel).align(Align.left);
        table.add(emailTF).colspan(2).width(TEXT_FIELD_WIDTH).row();
        table.add().padTop(GENERAL_HEIGHT_SPACING).row();

        table.add(passwordLabel).align(Align.left);
        table.add(passwordTF).colspan(2).width(TEXT_FIELD_WIDTH).row();
        table.add().padTop(GENERAL_HEIGHT_SPACING).row();

        table.add(genderLabel).align(Align.left);
        table.add(maleCB).align(Align.left);
        table.add(femaleCB).align(Align.right).row();
        table.row();
        table.add().padTop(GENERAL_HEIGHT_SPACING).row();

        table.add(cityLabel).align(Align.left);
        table.add(cityTF).colspan(2).width(TEXT_FIELD_WIDTH).row();
        table.add().padTop(GENERAL_HEIGHT_SPACING).row();

        table.add(tallLabel).align(Align.left);
        table.add(slider).colspan(2).width(TEXT_FIELD_WIDTH).row();
        table.add().padTop(GENERAL_HEIGHT_SPACING).row();

        table.add(countryLabel).height(BIG_TEXT_FIELD_HEIGHT).align(Align.left);
        table.row();
        table.add().padTop(GENERAL_HEIGHT_SPACING).row();

        table.add(degreeLabel).align(Align.left);
        table.row();
        table.add().padTop(GENERAL_HEIGHT_SPACING).row();

        table.add(activeCB).align(Align.left);
        table.row();
        table.add().padTop(GENERAL_HEIGHT_SPACING).row();

        table.add(commentTF).colspan(3).width(stage.getWidth() - TABLE_HORIZONTAL_PADDING * 2).height(BIG_TEXT_FIELD_HEIGHT).align(Align.left).row();
        table.add().padTop(GENERAL_HEIGHT_SPACING).row();

        table.add(clearTB).colspan(3).align(Align.right).row();
        table.add().padTop(GENERAL_HEIGHT_SPACING).row();

        table.add(signUpTB).colspan(3).width(300).height(100).align(Align.center).row();
        table.add().padTop(GENERAL_HEIGHT_SPACING).row();


        // adding everything to the stage
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
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
