package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;

public class ProfileWindow extends Window {

    public static final int DIALOG_HORIZONTAL_SPACING = 100;
    public static final int GENERAL_HEIGHT_SPACING = 50;
    public static final int TABLE_HORIZONTAL_PADDING = 30;
    public static final int TEXT_FIELD_WIDTH = 500;

    public ProfileWindow(String title, Skin skin, ProfileInfo profileInfo) {
        super(title, skin);

        Table localTable = new Table();
        localTable.setFillParent(true);
        localTable.align(Align.left);
        localTable.padTop(GENERAL_HEIGHT_SPACING / 2f);
        localTable.padRight(TABLE_HORIZONTAL_PADDING);
        localTable.padLeft(TABLE_HORIZONTAL_PADDING);
        localTable.padBottom(TABLE_HORIZONTAL_PADDING);

        // Labels styles and settings
        Label.LabelStyle titleLabelStyle = new Label.LabelStyle();
        titleLabelStyle.font = new BitmapFont(Gdx.files.internal("default.fnt"));
        titleLabelStyle.font.getData().setScale(3f);

        localTable.add(new Label("Your Info", titleLabelStyle)).colspan(2).align(Align.center).row();
        localTable.add().padTop(GENERAL_HEIGHT_SPACING).row();

        localTable.add(new Label("User Name:", skin)).padRight(DIALOG_HORIZONTAL_SPACING).align(Align.left);
        localTable.add(new Label(profileInfo.getUserName(), skin)).prefWidth(TEXT_FIELD_WIDTH).align(Align.left).row();
        localTable.add().padTop(GENERAL_HEIGHT_SPACING).row();

        localTable.add(new Label("Email:", skin)).align(Align.left);
        localTable.add(new Label(profileInfo.getEmail(), skin)).align(Align.left).row();
        localTable.add().padTop(GENERAL_HEIGHT_SPACING).row();

        localTable.add(new Label("Gender:", skin)).align(Align.left);
        localTable.add(new Label(profileInfo.getGender(), skin)).align(Align.left).row();
        localTable.add().padTop(GENERAL_HEIGHT_SPACING).row();

        localTable.add(new Label("City:", skin)).align(Align.left);
        localTable.add(new Label(profileInfo.getCities(), skin)).align(Align.left).row();
        localTable.add().padTop(GENERAL_HEIGHT_SPACING).row();

        localTable.add(new Label("Country:", skin)).align(Align.left);
        localTable.add(new Label(profileInfo.getCountry(), skin)).align(Align.left).row();
        localTable.add().padTop(GENERAL_HEIGHT_SPACING).row();

        localTable.add(new Label("Degree:", skin)).align(Align.left);
        localTable.add(new Label(profileInfo.getDegree(), skin)).align(Align.left).row();
        localTable.add().padTop(GENERAL_HEIGHT_SPACING).row();

        localTable.add(new Label("Active:", skin)).align(Align.left);
        localTable.add(new Label(profileInfo.getActive(), skin)).align(Align.left).row();
        localTable.add().padTop(GENERAL_HEIGHT_SPACING).row();

        localTable.add(new Label("Active:", skin)).align(Align.left);
        localTable.add(new Label(profileInfo.getActive(), skin)).align(Align.left).row();
        localTable.add().padTop(GENERAL_HEIGHT_SPACING).row();

        localTable.add(new Label("Active:", skin)).align(Align.left);
        localTable.add(new Label(profileInfo.getActive(), skin)).align(Align.left).row();
        localTable.add().padTop(GENERAL_HEIGHT_SPACING).row();

        TextButton cancelButton = new TextButton("OK", skin);
        cancelButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setVisible(false);
            }
        });

        localTable.add(cancelButton).prefWidth(150).prefHeight(70).colspan(3).align(Align.center);

        add(localTable).align(Align.topLeft);
    }

    public Window show(Stage stage){
        stage.addActor(this);
        stage.cancelTouchFocus();
        stage.setKeyboardFocus(this);
        stage.setScrollFocus(this);
        setPosition(Math.round((stage.getWidth() - getWidth()) / 2), Math.round((stage.getHeight() - getHeight()) / 2));
        return this;
    }
}
