package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.Timer;

public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch batch;
    private static final int GENERAL_HEIGHT_SPACING = 50;
    private static final int GENERAL_WIDTH_SPACING = 250;
    private static final int TEXT_FIELD_WIDTH = 500;
    private static final int TABLE_HORIZONTAL_PADDING = 30;
    private static final int TABLE_VERTICAL_PADDING = 50;
    private static final int BIG_TEXT_FIELD_HEIGHT = 220;
    private static final int dialogShowingTime = 1;


    private Stage stage;
    private Skin skin;

    private int currentDegreeValue = 0;
    TextField spinnerTF;

    BitmapFont font;

    @Override
    public void create() {

        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("default.fnt"));

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        Table table = new Table();
        table.setFillParent(true);
        table.align(Align.topLeft);
        table.padTop(TABLE_VERTICAL_PADDING);
        table.padLeft(TABLE_HORIZONTAL_PADDING);
        table.padRight(TABLE_HORIZONTAL_PADDING);

        skin.getDrawable("default-window").setMinWidth(500);
        skin.getDrawable("default-window").setMinHeight(500);


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

        // Create an array of options for the drop-down menu
        Array<String> options = new Array<>();
        options.add("City 1");
        options.add("City 2");
        options.add("City 3");

        // Create a SelectBox with the options and skin
        SelectBox<String> citiesSelectBox = new SelectBox<>(skin);
        citiesSelectBox.setItems(options);

        TextField commentTF = new TextArea("", skin);
        commentTF.setWidth(TEXT_FIELD_WIDTH);

        // adding the Check Box Group
        ButtonGroup<CheckBox> checkBoxGroup = new ButtonGroup<>();

        // Create a CheckBox with a label
        final CheckBox maleCB = new CheckBox("Male", skin);
        CheckBox femaleCB = new CheckBox("Female", skin);
        CheckBox activeCB = new CheckBox("Active", skin);

        // setting the min checks to be 0 and max checks to be 1
        checkBoxGroup.setMinCheckCount(0);
        checkBoxGroup.setMaxCheckCount(1);

        checkBoxGroup.add(maleCB, femaleCB);

        maleCB.addListener(new CheckBoxChangeListener());
        femaleCB.addListener(new CheckBoxChangeListener());

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

        Array<String> arrTextButtons = new Array<>();

        for (int i = 0; i < 10; i++){
            arrTextButtons.add("City" + (i + 1));
        }

        List<String> listTextButtons = new List<>(skin);
        listTextButtons.setItems(arrTextButtons);
        ScrollPane scrollPane = new ScrollPane(listTextButtons, skin);

        listTextButtons.addListener(new ButtonChangeListener());


        // making the spinner using two TextButtons and a TextField

        TextButton incrementTB = new TextButton("  -  ", skin);
        incrementTB.setWidth(100);
        incrementTB.addListener(new IncrementButtonChangeListener());
        spinnerTF = new TextField(String.valueOf(currentDegreeValue), skin);
        spinnerTF.setDisabled(true);
        spinnerTF.setWidth(TEXT_FIELD_WIDTH / 2f);
        TextButton decrementTB = new TextButton("  +  ", skin);
        decrementTB.addListener(new IncrementButtonChangeListener());


        HorizontalGroup hg = new HorizontalGroup();
        hg.fill();
        hg.space(10);
        hg.addActor(incrementTB);
        hg.addActor(spinnerTF);
        hg.addActor(decrementTB);

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
        table.add(citiesSelectBox).colspan(2).width(TEXT_FIELD_WIDTH).row();
        table.add().padTop(GENERAL_HEIGHT_SPACING).row();

        table.add(tallLabel).align(Align.left);
        table.add(slider).colspan(2).width(TEXT_FIELD_WIDTH).row();
        table.add().padTop(GENERAL_HEIGHT_SPACING).row();

        table.add(countryLabel).height(BIG_TEXT_FIELD_HEIGHT).align(Align.left);
        table.add(scrollPane).colspan(2).height(BIG_TEXT_FIELD_HEIGHT).width(TEXT_FIELD_WIDTH).row();
        table.add().padTop(GENERAL_HEIGHT_SPACING).row();

        table.add(degreeLabel).align(Align.left);
        table.add(hg).colspan(2).align(Align.center);
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
    }

    class CheckBoxChangeListener extends ChangeListener {
        @Override
        public void changed(ChangeEvent event, Actor actor) {
            if (((CheckBox) actor).isChecked()) {
                final Dialog d = new Dialog("", skin);
                d.text(((CheckBox) actor).getText().toString());
                d.getContentTable().padTop(80f);
                d.show(stage);
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        d.hide();
                    }
                }, dialogShowingTime);
            }
        }
    }

    class ButtonChangeListener extends ChangeListener {
        @Override
        public void changed(ChangeEvent event, Actor actor) {
            final Dialog d = new Dialog("", skin);
            d.text(((List) actor).getSelected().toString());
            d.getContentTable().padTop(80f);
            d.show(stage);
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    d.hide();
                }
            }, dialogShowingTime);
        }
    }

    class IncrementButtonChangeListener extends ChangeListener {
        @Override
        public void changed(ChangeEvent event, Actor actor) {
            if (((TextButton) actor).getText().toString().equals("  -  ")) {
                spinnerTF.setText(String.valueOf(--currentDegreeValue));
            } else if (((TextButton) actor).getText().toString().equals("  +  ")) {
                spinnerTF.setText(String.valueOf(++currentDegreeValue));
            }
        }
    }
}
