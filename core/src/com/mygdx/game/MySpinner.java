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

public class MySpinner extends Widget {

    private static final int SPINNER_HEIGHT = 80;
    private static final String INCREMENT_TEXT = "+";
    private static final String DECREMENT_TEXT = "-";
    private static final int SMALL_WIDTH_SPACING = 15;

    public int getCurrentDegreeValue() {
        return currentDegreeValue;
    }

    private int currentDegreeValue = 0;

    TextField spinnerTF;
    private final Skin skin;

    public MySpinner(Skin skin) {
        this.skin = skin;
    }

    public Table getWidget() {
        // making the custom TextButtonStyle
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("default-round"); // Set the up (default) drawable
        textButtonStyle.down = skin.getDrawable("default-round-down"); // Set the down drawable
        textButtonStyle.up.setRightWidth(SMALL_WIDTH_SPACING);
        textButtonStyle.down.setRightWidth(SMALL_WIDTH_SPACING);

        textButtonStyle.font = new BitmapFont(Gdx.files.internal("default.fnt"));
        textButtonStyle.font.getData().setScale(3f);

        // making the spinner using two TextButtons and a TextField
        TextButton incrementTB = new TextButton(DECREMENT_TEXT, textButtonStyle);
        incrementTB.addListener(new IncrementButtonChangeListener());

        spinnerTF = new TextField(String.valueOf(currentDegreeValue), skin);
        spinnerTF.setAlignment(Align.center);
        spinnerTF.setDisabled(true);

        TextButton decrementTB = new TextButton(INCREMENT_TEXT, textButtonStyle);
        decrementTB.addListener(new IncrementButtonChangeListener());

        Table horizontalTable = new Table();
        horizontalTable.add(incrementTB).prefHeight(SPINNER_HEIGHT).padRight(SMALL_WIDTH_SPACING);
        horizontalTable.add(spinnerTF).prefHeight(SPINNER_HEIGHT).padRight(SMALL_WIDTH_SPACING);
        horizontalTable.add(decrementTB).prefHeight(SPINNER_HEIGHT);

        return horizontalTable;
    }

    class IncrementButtonChangeListener extends ChangeListener {
        @Override
        public void changed(ChangeEvent event, Actor actor) {
            if (((TextButton) actor).getText().toString().equals(DECREMENT_TEXT)) {
                spinnerTF.setText(String.valueOf(--currentDegreeValue));
            } else if (((TextButton) actor).getText().toString().equals(INCREMENT_TEXT)) {
                spinnerTF.setText(String.valueOf(++currentDegreeValue));
            }
            fire(event);
        }
    }
}