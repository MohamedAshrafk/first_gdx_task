package com.mygdx.game;

import static com.mygdx.game.DrawingUtilities.createLineBorder;
import static com.mygdx.game.MyGdxGame.DIALOG_HEIGHT;
import static com.mygdx.game.MyGdxGame.DIALOG_WIDTH;
import static com.mygdx.game.DrawingUtilities.createCircularDrawable;
import static com.mygdx.game.DrawingUtilities.createRoundedDrawable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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
    public static final int BUTTON_HEIGHT = 70;
    public static final int BUTTON_WIDTH = 140;
    private static final int CANCEL_BUTTON_RADIUS = 30;
    private static final int WINDOW_ROUNDING_RADIUS = 30;
    private static final int HEADER_LINE_OFFSET = 35;
    private static final int HEADER_HEIGHT = 95;
    private static final int LINE_HEIGHT = 2;
    private static final int SCROLL_PANE_CUT_HEIGHT = (int) (BUTTON_HEIGHT * 4.4);

    /**
     * Creates a special kind of {@link Window} (like dialog) designed to take a list of {@link ProfileDataItem}
     * and present them in a suitable way with scrollable functionality
     *
     * @param profileData The list oof attributes
     * @param skin        the skin to be used on making the window
     */
    public ProfileWindow(List<ProfileDataItem> profileData, Skin skin) {
        super("", skin);

        padTop(0);
        padRight(0);
        padLeft(0);
        align(Align.top);
        background(createRoundedDrawable(Color.DARK_GRAY, DIALOG_WIDTH, DIALOG_HEIGHT, WINDOW_ROUNDING_RADIUS));

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

        Table headerTable = new Table();
        headerTable.padLeft(20);
        headerTable.padRight(30);
        headerTable.padTop(40);
        headerTable.padBottom(20);

        headerTable.background(createLineBorder(Color.LIGHT_GRAY, DIALOG_WIDTH, HEADER_HEIGHT, HEADER_LINE_OFFSET, HEADER_HEIGHT - 5, DIALOG_WIDTH - HEADER_LINE_OFFSET * 2, LINE_HEIGHT));

        // Create a skin
        Skin localSkin = new Skin(Gdx.files.internal("uiskin.json"));
        // Create a circular background for the button
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.up = createCircularDrawable(Color.DARK_GRAY, CANCEL_BUTTON_RADIUS);
        buttonStyle.down = createCircularDrawable(Color.RED, CANCEL_BUTTON_RADIUS);
        buttonStyle.font = localSkin.getFont("default-font");
        buttonStyle.fontColor = Color.WHITE;
        buttonStyle.font.getData().setScale(3f);


        TextButton cancelButton = new TextButton(" X ", buttonStyle);
        cancelButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setVisible(false);
            }
        });
        cancelButton.padTop(5).padRight(5);
        cancelButton.setWidth(CANCEL_BUTTON_RADIUS * 2);
        cancelButton.setWidth(CANCEL_BUTTON_RADIUS * 2);

        headerTable.add(cancelButton).prefWidth(CANCEL_BUTTON_RADIUS * 2).prefHeight(CANCEL_BUTTON_RADIUS * 2).padRight(530).align(Align.left);
        headerTable.add(new Label("Your Info", titleLabelStyle)).align(Align.right);


        add(headerTable).align(Align.left).row();
        add().padTop(GENERAL_HEIGHT_SPACING).row();

        for (ProfileDataItem profileItem : profileData) {
            localTable.add(new Label(profileItem.getAttributeName() + ":", skin)).padRight(DIALOG_HORIZONTAL_SPACING).align(Align.left);
            localTable.add(new Label(profileItem.getAttributeValue(), skin)).prefWidth(TEXT_FIELD_WIDTH).align(Align.left).row();
            localTable.add().padTop(GENERAL_HEIGHT_SPACING).row();
        }

        TextButton okButton = new TextButton("OK", skin);
        okButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setVisible(false);
            }
        });

        ScrollPane.ScrollPaneStyle scrollPaneStyle = new ScrollPane.ScrollPaneStyle();
        scrollPaneStyle.vScroll = skin.getDrawable("default-scroll");
        scrollPaneStyle.hScrollKnob = skin.getDrawable("default-round-large");
        scrollPaneStyle.background = null;
        scrollPaneStyle.hScroll = skin.getDrawable("default-scroll");
        scrollPaneStyle.vScrollKnob = skin.getDrawable("default-round-large");

        ScrollPane scrollPane = new ScrollPane(localTable, scrollPaneStyle);
        scrollPane.setFadeScrollBars(false);

        add(scrollPane).prefHeight(DIALOG_HEIGHT - SCROLL_PANE_CUT_HEIGHT).prefWidth(DIALOG_WIDTH - TABLE_HORIZONTAL_PADDING * 2).align(Align.center).row();
        add().padTop(GENERAL_HEIGHT_SPACING).row();
        add(okButton).prefWidth(BUTTON_WIDTH).prefHeight(BUTTON_HEIGHT).align(Align.center);
    }

    /**
     * Centers the window in the stage
     */
    public Window show(Stage stage) {
        stage.addActor(this);
        stage.cancelTouchFocus();
        stage.setKeyboardFocus(this);
        stage.setScrollFocus(this);
        setPosition(Math.round((stage.getWidth() - getWidth()) / 2), Math.round((stage.getHeight() - getHeight()) / 2));
        return this;
    }
}
