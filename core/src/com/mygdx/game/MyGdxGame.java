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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
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
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Objects;

public class MyGdxGame extends ApplicationAdapter {
    public static final int GENERAL_HEIGHT_SPACING = 50;
    public static final int GENERAL_WIDTH_SPACING = 250;
    public static final int SMALL_SPACING = 15;
    public static final int TEXT_FIELD_WIDTH = 500;
    public static final int TABLE_HORIZONTAL_PADDING = 30;
    public static final int TABLE_VERTICAL_PADDING = 50;
    public static final int BIG_TEXT_FIELD_HEIGHT = 220;
    public static final int SCREEN_WIDTH = 1080;
    public static final int SCREEN_HEIGHT = 2340;
    public static final int DIALOG_WIDTH = 900;
    public static final int DIALOG_HEIGHT = 1100;
    public static final int DIALOG_HORIZONTAL_SPACING = 100;

    private Stage stage;
    private Viewport viewport;
    private Skin skin;
    private Table table;
    private LabelStyle labelStyle;

    private String preSelectedSelectBoxValue = "";

    private ProgressBar progressBar;

    private TextField userNameTF;
    private TextField emailTF;
    private TextField passwordTF;
    private ButtonGroup<CheckBox> genderCBGroup;
    private SelectBox<String> citiesSelectBox;
    private List<String> countryList;
    private MySpinner degreeSpinner;
    private CheckBox activeCB;
    private TextField commentTF;

    @Override
    public void create() {

        viewport = new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        skin.getFont("default-font").getData().setScale(2.5f);

        table = new Table();
        table.setFillParent(true);
        table.align(Align.topLeft);
        table.padTop(TABLE_VERTICAL_PADDING);
        table.padLeft(TABLE_HORIZONTAL_PADDING);
        table.padRight(TABLE_HORIZONTAL_PADDING);


//        skin.getDrawable("default-window").setMinWidth(DIALOG_WIDTH);
//        skin.getDrawable("default-window").setMinHeight(DIALOG_HEIGHT);

//        table.debug();

        // Labels styles and settings
        labelStyle = new LabelStyle();
        labelStyle.font = new BitmapFont(Gdx.files.internal("default.fnt"));
        labelStyle.font.getData().setScale(3f);

        configureAll();

        // adding everything to the stage
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewport.update(width, height);
    }

    private void configureAll() {
        configureCommentArea();
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

    public void configureCommentArea() {
        // Create a skin (you can use the default skin or create your own)
        Skin localSkin = new Skin(Gdx.files.internal("uiskin.json"));

        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.background = localSkin.getDrawable("textfield");
        textFieldStyle.font = localSkin.getFont("default-font");
        textFieldStyle.fontColor = localSkin.getColor("white");
        textFieldStyle.font.getData().setScale(2.0f);

        textFieldStyle.background.setLeftWidth(SMALL_SPACING);
        textFieldStyle.background.setTopHeight(SMALL_SPACING);

        commentTF = new TextArea("", textFieldStyle);
        commentTF.setDisabled(true);
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

        userNameTF.addListener(textFieldChangeListener("User Name"));

        table.add(userNameLabel).padRight(GENERAL_WIDTH_SPACING).align(Align.left);
        table.add(userNameTF).colspan(2).width(TEXT_FIELD_WIDTH).row();
        table.add().padTop(GENERAL_HEIGHT_SPACING).row();
    }

    private void configureEmailField() {

        Label emailLabel = new Label("Email", labelStyle);

        emailTF = new TextField("", skin);
        emailTF.setWidth(TEXT_FIELD_WIDTH);

        emailTF.addListener(textFieldChangeListener("Email"));

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
        genderCBGroup = new ButtonGroup<>();

        // Create a CheckBox with a label
        final CheckBox maleCB = new CheckBox("Male", skin);
        CheckBox femaleCB = new CheckBox("Female", skin);

        // setting the min checks to be 0 and max checks to be 1
        genderCBGroup.setMinCheckCount(1);
        genderCBGroup.setMaxCheckCount(1);

        genderCBGroup.add(maleCB, femaleCB);

        maleCB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (((CheckBox) actor).isChecked()) {
                    String addedText = ((CheckBox) actor).getText().toString();

                    String commentString = "Gender: " + addedText + "\n";
                    commentTF.appendText(commentString);
                }
            }
        });
        femaleCB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (((CheckBox) actor).isChecked()) {
                    String addedText = ((CheckBox) actor).getText().toString();

                    String commentString = "Gender: " + addedText + "\n";
                    commentTF.appendText(commentString);
                }
            }
        });

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
        citiesSelectBox = new SelectBox<>(skin);
        citiesSelectBox.setItems(options);

        citiesSelectBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                String addedText = ((SelectBox<?>) actor).getSelected().toString();
                if (!Objects.equals(preSelectedSelectBoxValue, addedText)) {
                    String commentString = "City: " + addedText + "\n";
                    preSelectedSelectBoxValue = addedText;
                    commentTF.appendText(commentString);
                }
            }
        });

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

        countryList = new List<>(skin);
        countryList.setItems(arrTextButtons);
        ScrollPane scrollPane = new ScrollPane(countryList, skin);

        countryList.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                String addedText = ((List<?>) actor).getSelected().toString();
                String commentString = "Country: " + addedText + "\n";
                commentTF.appendText(commentString);
            }
        });

        table.add(countryLabel).height(BIG_TEXT_FIELD_HEIGHT).align(Align.left);
        table.add(scrollPane).colspan(2).height(BIG_TEXT_FIELD_HEIGHT).width(TEXT_FIELD_WIDTH).row();
        table.add().padTop(GENERAL_HEIGHT_SPACING).row();
    }

    private void configureDegreeField() {
        Label degreeLabel = new Label("Degree", labelStyle);

        degreeSpinner = new MySpinner(skin, 90, 0, 100, 5);
        degreeSpinner.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                String commentString = "Degree: " + degreeSpinner.getValue() + "\n";
                commentTF.appendText(commentString);
            }
        });

        table.add(degreeLabel).align(Align.left);
        table.add(degreeSpinner).align(Align.center).colspan(2);
        table.row();
        table.add().padTop(GENERAL_HEIGHT_SPACING).row();
    }

    private void configureActiveField() {
        activeCB = new CheckBox("Active", skin);

        activeCB.getImage().setScaling(Scaling.fill);
        activeCB.getImageCell().size(50);

        activeCB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                String commentString;
                if (((CheckBox) actor).isChecked()) {
                    commentString = "Active: " + "true" + "\n";
                } else {
                    commentString = "Active: " + "false" + "\n";
                }
                commentTF.appendText(commentString);
            }
        });

        table.add(activeCB).align(Align.left);
        table.row();
        table.add().padTop(GENERAL_HEIGHT_SPACING).row();
    }

    private void configureCommentField() {

        // Create a TextFieldStyle based on the skin

        table.add(commentTF).colspan(3).prefWidth(stage.getWidth() - TABLE_HORIZONTAL_PADDING * 2).prefHeight(BIG_TEXT_FIELD_HEIGHT).align(Align.left).row();
        table.add().padTop(GENERAL_HEIGHT_SPACING).row();
    }

    private void configureBottomButtonsField() {
        TextButton clearTB = new TextButton("Clear", skin);
        TextButton signUpTB = new TextButton("Sign Up", skin);

        table.add(clearTB).colspan(3).align(Align.right).row();
        table.add().padTop(GENERAL_HEIGHT_SPACING).row();

        signUpTB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (!userNameTF.getText().isEmpty() && !emailTF.getText().isEmpty() && !passwordTF.getText().isEmpty()) {
                    progressBar.setVisible(true);
                    doProgress();
                } else {
                    String commentString = "missing fields" + "\n";
                    commentTF.appendText(commentString);
                }
            }
        });
        clearTB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                progressBar.setValue(0);
                commentTF.setText("");
            }
        });

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

    public ChangeListener textFieldChangeListener(final String fieldName) {
        return new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                String addedText = ((TextField) actor).getText();

                String commentString = fieldName + ": " + addedText + "\n";
                commentTF.appendText(commentString);
            }
        };
    }


    private void doProgress() {
        final Timer.Task t1 = new Timer.Task() {
            float progressValue = progressBar.getValue();

            @Override
            public void run() {
                float progressSpeed = 50f;

                // Increment the progress value based on time and speed
                progressValue += progressSpeed * Gdx.graphics.getDeltaTime();

                // Ensure the progress value stays within the bounds of the ProgressBar
                if (progressValue > progressBar.getMaxValue()) {
                    this.cancel();
                    progressBar.setVisible(false);
                    progressValue = 0;
                    showUserInfo();
                }

                // Update the ProgressBar value
                progressBar.setValue(progressValue);
            }
        };
        Timer.schedule(t1, 0f, 0.01f);
    }

    private void showUserInfo() {
        final ProfileInfo profileInfo = new ProfileInfo(
                userNameTF.getText(),
                emailTF.getText(),
                genderCBGroup.getChecked().getText().toString(),
                citiesSelectBox.getSelected(),
                countryList.getSelected(),
                String.valueOf(degreeSpinner.getValue()),
                activeCB.isChecked() ? "Yes" : "No"
        );

        final ProfileWindow profileWindow = new ProfileWindow("", skin, profileInfo);
        profileWindow.setWidth(DIALOG_WIDTH);
        profileWindow.setHeight(DIALOG_HEIGHT);

        profileWindow.show(stage);
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
}
