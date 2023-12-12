package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;

import java.util.List;

public class ProfileWindow extends Window {

    public static final int DIALOG_HORIZONTAL_SPACING = 100;
    public static final int GENERAL_HEIGHT_SPACING = 30;
    public static final int TABLE_HORIZONTAL_PADDING = 30;
    public static final int TEXT_FIELD_WIDTH = 400;
    public static final int DIALOG_WIDTH = 900;
    public static final int DIALOG_HEIGHT = 1100;
    public static final int BUTTON_HEIGHT = 70;
    public static final int BUTTON_WIDTH = 140;

    /**
     * Creates a special kind of {@link Window} (like dialog) designed to take a list of {@link ProfileDataItem}
     * and present them in a suitable way with scrollable functionality
     *
     * @param profileData The list oof attributes
     * @param skin        the skin to be used on making the window
     */
    public ProfileWindow(List<ProfileDataItem> profileData, Skin skin) {
        super("", skin);


        Table localTable = new Table();
//        localTable.setFillParent(true);
        localTable.align(Align.topLeft);
        localTable.padTop(GENERAL_HEIGHT_SPACING);
        localTable.padRight(TABLE_HORIZONTAL_PADDING);
        localTable.padLeft(TABLE_HORIZONTAL_PADDING);
        localTable.padBottom(TABLE_HORIZONTAL_PADDING);

        // Labels styles and settings
        Label.LabelStyle titleLabelStyle = new Label.LabelStyle();
        titleLabelStyle.font = new BitmapFont(Gdx.files.internal("default.fnt"));
        titleLabelStyle.font.getData().setScale(3f);

        add(new Label("Your Info", titleLabelStyle)).colspan(2).align(Align.center).row();

        for (ProfileDataItem profileItem : profileData) {
            localTable.add(new Label(profileItem.getAttributeName() + ":", skin)).padRight(DIALOG_HORIZONTAL_SPACING).align(Align.left);
            localTable.add(new Label(profileItem.getAttributeValue(), skin)).prefWidth(TEXT_FIELD_WIDTH).align(Align.left).row();
            localTable.add().padTop(GENERAL_HEIGHT_SPACING).row();
        }

        TextButton cancelButton = new TextButton("OK", skin);
        cancelButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setVisible(false);
            }
        });

        ScrollPane scrollPane = new ScrollPane(localTable, skin);

        add(scrollPane).prefHeight(DIALOG_HEIGHT - BUTTON_HEIGHT * 3).prefWidth(DIALOG_WIDTH - TABLE_HORIZONTAL_PADDING * 2).align(Align.topLeft).row();
        add().padTop(GENERAL_HEIGHT_SPACING).row();
        add(cancelButton).prefWidth(BUTTON_WIDTH).prefHeight(BUTTON_HEIGHT).align(Align.center);
    }
    /** Centers the window in the stage
     * */
    public Window show(Stage stage) {
        stage.addActor(this);
        stage.cancelTouchFocus();
        stage.setKeyboardFocus(this);
        stage.setScrollFocus(this);
        setPosition(Math.round((stage.getWidth() - getWidth()) / 2), Math.round((stage.getHeight() - getHeight()) / 2));
        return this;
    }
}
