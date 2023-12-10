package com.mygdx.game;

import static com.mygdx.game.MyGdxGame.SMALL_SPACING;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;

public class MySpinner extends Table {

    private static final int SPINNER_DIMENSION = 55;
    private static final String INCREMENT_TEXT = "+";
    private static final String DECREMENT_TEXT = "-";

    public int getCurrentDegreeValue() {
        return currentDegreeValue;
    }

    private int currentDegreeValue;

    public boolean isValidValue() {
        return isValidValue;
    }

    private boolean isValidValue = true;
    private final int minDegreeVal;
    private final int maxDegreeVal;

    TextField spinnerTF;

    public MySpinner(Skin skin) {
        this(skin, 0, 0, 100);
    }

    public MySpinner(Skin skin, int startValue, int minDegreeVal, int maxDegreeVal) {

        this.currentDegreeValue = startValue;
        this.minDegreeVal = minDegreeVal;
        this.maxDegreeVal = maxDegreeVal;

        // making the custom TextButtonStyle
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("default-round"); // Set the up (default) drawable
        textButtonStyle.down = skin.getDrawable("default-round-down"); // Set the down drawable
        textButtonStyle.up.setRightWidth(SMALL_SPACING);
        textButtonStyle.down.setRightWidth(SMALL_SPACING);

        textButtonStyle.font = new BitmapFont(Gdx.files.internal("default.fnt"));
        textButtonStyle.font.getData().setScale(3f);

        // making the spinner using two TextButtons and a TextField
        TextButton decrementTB = new TextButton(DECREMENT_TEXT, textButtonStyle);
        decrementTB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (validateValue(currentDegreeValue - 1)) {
                    isValidValue = true;
                    spinnerTF.setText(String.valueOf(--currentDegreeValue));
                } else
                    isValidValue = false;
            }
        });

        spinnerTF = new TextField(String.valueOf(currentDegreeValue), skin);
        spinnerTF.setAlignment(Align.center);
        spinnerTF.setDisabled(true);

        TextButton incrementTB = new TextButton(INCREMENT_TEXT, textButtonStyle);
        incrementTB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (validateValue(currentDegreeValue + 1)){
                    isValidValue = true;
                    spinnerTF.setText(String.valueOf(++currentDegreeValue));
                } else
                    isValidValue = false;
            }
        });

        add(decrementTB).prefWidth(SPINNER_DIMENSION).prefHeight(SPINNER_DIMENSION).padRight(SMALL_SPACING);
        add(spinnerTF).prefHeight(75f).padRight(SMALL_SPACING);
        add(incrementTB).prefWidth(SPINNER_DIMENSION).prefHeight(SPINNER_DIMENSION);
    }

    private boolean validateValue(int value) {
        return value <= maxDegreeVal && value >= minDegreeVal;
    }
}