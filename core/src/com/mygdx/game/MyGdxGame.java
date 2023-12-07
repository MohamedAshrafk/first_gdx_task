package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.Timer;

import java.util.Objects;

public class MyGdxGame extends ApplicationAdapter {
    private static final int GENERAL_HEIGHT_SPACING = 50;
    private static final int GENERAL_WIDTH_SPACING = 250;
    private static final int SMALL_WIDTH_SPACING = 15;
    private static final int TEXT_FIELD_WIDTH = 500;
    private static final int TABLE_HORIZONTAL_PADDING = 30;
    private static final int TABLE_VERTICAL_PADDING = 50;
    private static final int BIG_TEXT_FIELD_HEIGHT = 220;
    private static final int dialogShowingTime = 1;

    private Stage stage;
    private Skin skin;
    private Table table;
    LabelStyle labelStyle;

    TextField commentTF;
    String commentString = "";
    ScrollPane commentScrollPane;

    String preSelectedSelectBoxValue = "";

    private int currentDegreeValue = 0;
    TextField spinnerTF;
    ProgressBar progressBar;

    TextField userNameTF;
    TextField emailTF;
    TextField passwordTF;

    BitmapFont font;

    @Override
    public void create() {

        font = new BitmapFont(Gdx.files.internal("default.fnt"));

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        skin.getFont("default-font").getData().setScale(2.5f);

        table = new Table();
        table.setFillParent(true);
        table.align(Align.topLeft);
        table.padTop(TABLE_VERTICAL_PADDING);
        table.padLeft(TABLE_HORIZONTAL_PADDING);
        table.padRight(TABLE_HORIZONTAL_PADDING);

        skin.getDrawable("default-window").setMinWidth(500);
        skin.getDrawable("default-window").setMinHeight(500);

        // Labels styles and settings
        labelStyle = new LabelStyle();
        labelStyle.font = new BitmapFont(Gdx.files.internal("default.fnt"));
        labelStyle.font.getData().setScale(3f);

        configureAll();

        // adding everything to the stage
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
    }

    private void configureAll() {
        configureTitleField();
        configureNameField();
        configureEmailField();
        configurePasswordField();
        configureGenderField();
        configureCityField();
        configureTallField();
        configureCountryField();
        configureDegreeField();
        configureActiveField();
        configureCommentField();
        configureBottomButtonsField();
        configureProgressBarField();
    }

    private void configureTitleField() {
        // Labels styles and settings
        LabelStyle labelStyle = new LabelStyle();
        labelStyle.font = new BitmapFont(Gdx.files.internal("default.fnt"));
        labelStyle.font.getData().setScale(4f);

        Label signUpLabel = new Label("Sign Up", labelStyle);

        table.add(signUpLabel).colspan(3).align(Align.center).row();
        table.add().padTop(GENERAL_HEIGHT_SPACING).row();
    }

    private void configureNameField() {

        Label userNameLabel = new Label("User Name", labelStyle);

        userNameTF = new TextField("", skin);
        userNameTF.setWidth(TEXT_FIELD_WIDTH);

        userNameTF.addListener(new TextFieldChangeListener("User Name"));

        table.add(userNameLabel).padRight(GENERAL_WIDTH_SPACING).align(Align.left);
        table.add(userNameTF).colspan(2).width(TEXT_FIELD_WIDTH).row();
        table.add().padTop(GENERAL_HEIGHT_SPACING).row();
    }

    private void configureEmailField() {

        Label emailLabel = new Label("Email", labelStyle);

        emailTF = new TextField("", skin);
        emailTF.setWidth(TEXT_FIELD_WIDTH);

        emailTF.addListener(new TextFieldChangeListener("Email"));

        table.add(emailLabel).align(Align.left);
        table.add(emailTF).colspan(2).width(TEXT_FIELD_WIDTH).row();
        table.add().padTop(GENERAL_HEIGHT_SPACING).row();
    }

    private void configurePasswordField() {

        Label passwordLabel = new Label("Password", labelStyle);

        passwordTF = new TextField("", skin);
        passwordTF.setWidth(TEXT_FIELD_WIDTH);

        passwordTF.setPasswordMode(true);
        passwordTF.setPasswordCharacter('*'); // Optional: customize the password character

        table.add(passwordLabel).align(Align.left);
        table.add(passwordTF).colspan(2).width(TEXT_FIELD_WIDTH).row();
        table.add().padTop(GENERAL_HEIGHT_SPACING).row();

    }

    private void configureGenderField() {

        Label genderLabel = new Label("Gender", labelStyle);

        // adding the Check Box Group
        ButtonGroup<CheckBox> checkBoxGroup = new ButtonGroup<>();

        // Create a CheckBox with a label
        final CheckBox maleCB = new CheckBox("Male", skin);
        CheckBox femaleCB = new CheckBox("Female", skin);

        // setting the min checks to be 0 and max checks to be 1
        checkBoxGroup.setMinCheckCount(1);
        checkBoxGroup.setMaxCheckCount(1);

        checkBoxGroup.add(maleCB, femaleCB);

        maleCB.addListener(new CheckBoxChangeListener());
        femaleCB.addListener(new CheckBoxChangeListener());

        // making the image assets bigger for the CheckBox widget
        maleCB.getImage().setScaling(Scaling.fill);
        maleCB.getImageCell().size(50);
        femaleCB.getImage().setScaling(Scaling.fill);
        femaleCB.getImageCell().size(50);

        table.add(genderLabel).align(Align.left);
        table.add(maleCB).align(Align.left);
        table.add(femaleCB).align(Align.right).row();
        table.row();
        table.add().padTop(GENERAL_HEIGHT_SPACING).row();
    }

    private void configureCityField() {

        Label cityLabel = new Label("City", labelStyle);

        // Create an array of options for the drop-down menu
        Array<String> options = new Array<>();

        for (int i = 0; i < 6; i++) {
            options.add("City" + (i + 1));
        }

        // Create a SelectBox with the options and skin
        SelectBox<String> citiesSelectBox = new SelectBox<>(skin);
        citiesSelectBox.setItems(options);

        citiesSelectBox.addListener(new ListsSelectedChangeListener());

        table.add(cityLabel).align(Align.left);
        table.add(citiesSelectBox).colspan(2).width(TEXT_FIELD_WIDTH).row();
        table.add().padTop(GENERAL_HEIGHT_SPACING).row();
    }

    private void configureTallField() {

        Label tallLabel = new Label("Tall", labelStyle);

        // Create a SliderStyle (optional)
        Slider.SliderStyle sliderStyle = new Slider.SliderStyle();
        sliderStyle.background = skin.getDrawable("default-slider"); // Set the background drawable
        sliderStyle.knob = skin.getDrawable("default-slider-knob"); // Set the knob drawable
        sliderStyle.knob.setMinWidth(50);
        sliderStyle.knob.setMinHeight(50);
        sliderStyle.background.setMinHeight(10);

        // Create a Slider with a range and the defined style
        Slider slider = new Slider(0, 100, 1, false, sliderStyle);

        table.add(tallLabel).align(Align.left);
        table.add(slider).colspan(2).width(TEXT_FIELD_WIDTH).row();
        table.add().padTop(GENERAL_HEIGHT_SPACING).row();
    }

    private void configureCountryField() {

        Label countryLabel = new Label("Country", labelStyle);

        Array<String> arrTextButtons = new Array<>();

        for (int i = 0; i < 10; i++) {
            arrTextButtons.add("Country" + (i + 1));
        }

        List<String> listTextButtons = new List<>(skin);
        listTextButtons.setItems(arrTextButtons);
        ScrollPane scrollPane = new ScrollPane(listTextButtons, skin);

        listTextButtons.addListener(new ListsSelectedChangeListener());

        table.add(countryLabel).height(BIG_TEXT_FIELD_HEIGHT).align(Align.left);
        table.add(scrollPane).colspan(2).height(BIG_TEXT_FIELD_HEIGHT).width(TEXT_FIELD_WIDTH).row();
        table.add().padTop(GENERAL_HEIGHT_SPACING).row();
    }

    private void configureDegreeField() {
        Label degreeLabel = new Label("Degree", labelStyle);

        // making the custom TextButtonStyle
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("default-round"); // Set the up (default) drawable
        textButtonStyle.down = skin.getDrawable("default-round-down"); // Set the down drawable

        textButtonStyle.font = new BitmapFont(Gdx.files.internal("default.fnt"));
        textButtonStyle.font.getData().setScale(4f);

        // making the spinner using two TextButtons and a TextField
        TextButton incrementTB = new TextButton("- ", textButtonStyle);
        incrementTB.addListener(new IncrementButtonChangeListener());

        spinnerTF = new TextField(String.valueOf(currentDegreeValue), skin);
        spinnerTF.setDisabled(true);
        spinnerTF.setWidth(TEXT_FIELD_WIDTH / 2f);
        spinnerTF.setAlignment(Align.center);

        TextButton decrementTB = new TextButton("+ ", textButtonStyle);
        decrementTB.addListener(new IncrementButtonChangeListener());

        HorizontalGroup horizontalGroup = new HorizontalGroup();
        horizontalGroup.fill();
        horizontalGroup.space(SMALL_WIDTH_SPACING);
        horizontalGroup.addActor(incrementTB);
        horizontalGroup.addActor(spinnerTF);
        horizontalGroup.addActor(decrementTB);

        table.add(degreeLabel).align(Align.left);
        table.add(horizontalGroup).colspan(2).align(Align.center);
        table.row();
        table.add().padTop(GENERAL_HEIGHT_SPACING).row();
    }

    private void configureActiveField() {
        CheckBox activeCB = new CheckBox("Active", skin);

        activeCB.getImage().setScaling(Scaling.fill);
        activeCB.getImageCell().size(50);

        activeCB.addListener(new ActiveChangeListener());

        table.add(activeCB).align(Align.left);
        table.row();
        table.add().padTop(GENERAL_HEIGHT_SPACING).row();
    }

    private void configureCommentField() {
        commentTF = new TextArea("", skin);
        commentTF.setWidth(TEXT_FIELD_WIDTH);
        commentTF.setDisabled(true);

        commentScrollPane = new ScrollPane(commentTF, skin);
        commentScrollPane.setFadeScrollBars(false);

        table.add(commentScrollPane).colspan(3).width(stage.getWidth() - TABLE_HORIZONTAL_PADDING * 2).height(BIG_TEXT_FIELD_HEIGHT).align(Align.left).row();
        table.add().padTop(GENERAL_HEIGHT_SPACING).row();
    }

    private void configureBottomButtonsField() {
        TextButton clearTB = new TextButton("Clear", skin);
        TextButton signUpTB = new TextButton("Sign Up", skin);

        table.add(clearTB).colspan(3).align(Align.right).row();
        table.add().padTop(GENERAL_HEIGHT_SPACING).row();

        signUpTB.addListener(new SignupButtonClickListener());
        clearTB.addListener(new ClearButtonClickListener());

        table.add(signUpTB).colspan(3).width(300).height(100).align(Align.center).row();
        table.add().padTop(GENERAL_HEIGHT_SPACING).row();
    }

    private void configureProgressBarField() {
        // Create a SliderStyle
        ProgressBar.ProgressBarStyle progressBarStyle = new ProgressBar.ProgressBarStyle();

        // Create a TextureRegionDrawable from the TextureRegion
        progressBarStyle.knobAfter = new TextureRegionDrawable(getTexture(Color.DARK_GRAY));
        progressBarStyle.knobAfter.setMinHeight(15f);

        // Create a TextureRegionDrawable from the TextureRegion
        progressBarStyle.knobBefore = new TextureRegionDrawable(getTexture(Color.GREEN));
        progressBarStyle.knobBefore.setMinHeight(15f);

        progressBar = new ProgressBar(0f, 100f, 1f, false, progressBarStyle);
        progressBar.setStyle(progressBarStyle);

        progressBar.setValue(0f);
        progressBar.setVisible(false);
        table.add(progressBar).colspan(3).width(stage.getWidth() - TABLE_HORIZONTAL_PADDING * 2).height(50f).align(Align.center).row();
        table.add().padTop(GENERAL_HEIGHT_SPACING).row();
    }

    static public TextureRegion getTexture(Color color) {
        // Create a Pixmap with the White color
        Pixmap pixmapW = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmapW.setColor(color);
        pixmapW.fill();

        // Create a TextureRegion from the Pixmap
        Texture texture = new Texture(pixmapW);
        return new TextureRegion(texture);
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
        stage.dispose();
        skin.dispose();
    }

    class CheckBoxChangeListener extends ChangeListener {
        @Override
        public void changed(ChangeEvent event, Actor actor) {
            if (((CheckBox) actor).isChecked()) {
                String addedText = ((CheckBox) actor).getText().toString();

                commentString = "Gender: " + addedText + "\n";
                commentTF.appendText(commentString);
            }
        }
    }

    class TextFieldChangeListener extends ChangeListener {
        private final String fieldName;

        public TextFieldChangeListener(String user_name) {
            this.fieldName = user_name;
        }

        @Override
        public void changed(ChangeEvent event, Actor actor) {
            String addedText = ((TextField) actor).getText();

            commentString = fieldName + ": " + addedText + "\n";
            commentTF.appendText(commentString);
        }
    }

    class ListsSelectedChangeListener extends ChangeListener {
        @Override
        public void changed(ChangeEvent event, Actor actor) {
            String addedText;
            if (actor.getClass() == List.class) {
                addedText = ((List<?>) actor).getSelected().toString();
                commentString = "Country: " + addedText + "\n";
                commentTF.appendText(commentString);
            } else if (actor.getClass() == SelectBox.class) {
                addedText = ((SelectBox<?>) actor).getSelected().toString();
                if (!Objects.equals(preSelectedSelectBoxValue, addedText)) {
                    commentString = "City: " + addedText + "\n";
                    preSelectedSelectBoxValue = addedText;
                    commentTF.appendText(commentString);
                }
            }
        }
    }

    class IncrementButtonChangeListener extends ChangeListener {
        @Override
        public void changed(ChangeEvent event, Actor actor) {
            if (((TextButton) actor).getText().toString().equals("- ")) {
                spinnerTF.setText(String.valueOf(--currentDegreeValue));
            } else if (((TextButton) actor).getText().toString().equals("+ ")) {
                spinnerTF.setText(String.valueOf(++currentDegreeValue));
            }
            commentString = "Degree: " + currentDegreeValue + "\n";
            commentTF.appendText(commentString);
        }
    }

    class ActiveChangeListener extends ChangeListener {
        @Override
        public void changed(ChangeEvent event, Actor actor) {
            if (((CheckBox) actor).isChecked()) {
                commentString = "Active: " + "true" + "\n";
            } else {
                commentString = "Active: " + "false" + "\n";
            }
            commentTF.appendText(commentString);
        }
    }

    class SignupButtonClickListener extends ClickListener {
        @Override
        public void clicked(InputEvent event, float x, float y) {
            super.clicked(event, x, y);

            if (!userNameTF.getText().isEmpty() && !emailTF.getText().isEmpty() && !passwordTF.getText().isEmpty()) {
                progressBar.setVisible(true);
                makeItProgress();
            } else {
                commentString = "missing fields" + "\n";
                commentTF.appendText(commentString);
            }

        }

        private void makeItProgress() {
            final Timer.Task t1 = new Timer.Task() {
                float progressValue = progressBar.getValue();

                @Override
                public void run() {
                    float progressSpeed = 50f;

                    // Increment the progress value based on time and speed
                    progressValue += progressSpeed * Gdx.graphics.getDeltaTime();

                    // Ensure the progress value stays within the bounds of the ProgressBar
                    if (progressValue > progressBar.getMaxValue()) {
                        progressValue = progressBar.getMaxValue();
                        progressBar.setVisible(false);
                        this.cancel();
                    }

                    // Update the ProgressBar value
                    progressBar.setValue(progressValue);
                }
            };
            Timer.schedule(t1, 0f, 0.01f);
        }
    }

    class ClearButtonClickListener extends ClickListener {
        @Override
        public void clicked(InputEvent event, float x, float y) {
            super.clicked(event, x, y);

            progressBar.setValue(0);
            commentString = "";
            commentTF.setText(commentString);
        }
    }
}
