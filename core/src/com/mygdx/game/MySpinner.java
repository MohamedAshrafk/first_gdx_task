package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;

public class MySpinner extends Table {

    private static final int SPINNER_HEIGHT = 80;
    private static final String INCREMENT_TEXT = "+";
    private static final String DECREMENT_TEXT = "-";
    private static final int SMALL_WIDTH_SPACING = 15;

    public int getCurrentDegreeValue() {
        return currentDegreeValue;
    }

    private int currentDegreeValue = 0;

    TextField spinnerTF;

    public MySpinner(Skin skin) {

        // making the custom TextButtonStyle
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("default-round"); // Set the up (default) drawable
        textButtonStyle.down = skin.getDrawable("default-round-down"); // Set the down drawable
        textButtonStyle.up.setRightWidth(SMALL_WIDTH_SPACING);
        textButtonStyle.down.setRightWidth(SMALL_WIDTH_SPACING);

        textButtonStyle.font = new BitmapFont(Gdx.files.internal("default.fnt"));
        textButtonStyle.font.getData().setScale(3f);

        // making the spinner using two TextButtons and a TextField
        TextButton decrementTB = new TextButton(DECREMENT_TEXT, textButtonStyle);
        decrementTB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                spinnerTF.setText(String.valueOf(--currentDegreeValue));
            }
        });

        spinnerTF = new TextField(String.valueOf(currentDegreeValue), skin);
        spinnerTF.setAlignment(Align.center);
        spinnerTF.setDisabled(true);

        TextButton incrementTB = new TextButton(INCREMENT_TEXT, textButtonStyle);
        incrementTB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                spinnerTF.setText(String.valueOf(++currentDegreeValue));
            }
        });

        add(decrementTB).prefHeight(SPINNER_HEIGHT).padRight(SMALL_WIDTH_SPACING);
        add(spinnerTF).prefHeight(SPINNER_HEIGHT).padRight(SMALL_WIDTH_SPACING);
        add(incrementTB).prefHeight(SPINNER_HEIGHT);
    }
}