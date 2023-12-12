package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

import java.util.List;

public class ProfileWindow extends Window {

    public static final int DIALOG_HORIZONTAL_SPACING = 100;
    public static final int GENERAL_HEIGHT_SPACING = 30;
    public static final int TABLE_HORIZONTAL_PADDING = 30;
    public static final int TEXT_FIELD_WIDTH = 400;
    public static final int DIALOG_WIDTH = 900;
    public static final int DIALOG_HEIGHT = 1200;
    public static final int BUTTON_HEIGHT = 70;
    public static final int BUTTON_WIDTH = 140;

//    @Override
//    public void draw(Batch batch, float parentAlpha) {
//        ShapeRenderer shapeRenderer = new ShapeRenderer();
//        // Draw a background with a header using ShapeRenderer
//        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//
//        // Draw background
//        shapeRenderer.setColor(Color.GRAY);
//        shapeRenderer.rect(getX(), getY(), getWidth(), getHeight());
//
//        // Draw header
//        shapeRenderer.setColor(Color.DARK_GRAY);
//        shapeRenderer.rect(getX(), getY() + getHeight() - getTitleLabel().getHeight(), getWidth(), getTitleLabel().getHeight());
//
//        shapeRenderer.end();
//
//        super.draw(batch, parentAlpha);
//    }


//    @Override
//    protected void drawBackground(Batch batch, float parentAlpha, float x, float y) {
//        super.drawBackground(batch, parentAlpha, x, y);
//
//        ShapeRenderer shapeRenderer = new ShapeRenderer();
//        // Draw a background with a header using ShapeRenderer
//        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//
//        // Draw background
//        shapeRenderer.setColor(Color.GRAY);
//        shapeRenderer.rect(getX(), getY(), getWidth(), getHeight());
//
//        // Draw header
//        shapeRenderer.setColor(Color.DARK_GRAY);
//        shapeRenderer.rect(getX(), getY() + getHeight() - getTitleLabel().getHeight(), getWidth(), getTitleLabel().getHeight());
//
//        shapeRenderer.end();
//
//        super.draw(batch, parentAlpha);
//    }

    /**
     * Creates a special kind of {@link Window} (like dialog) designed to take a list of {@link ProfileDataItem}
     * and present them in a suitable way with scrollable functionality
     *
     * @param profileData The list oof attributes
     * @param skin        the skin to be used on making the window
     */
    public ProfileWindow(List<ProfileDataItem> profileData, Skin skin) {
        super("", skin);

//        background(new TextureRegionDrawable(getTexture(Color.GREEN)));

        padRight(0);
        padLeft(0);


        Table localTable = new Table();
//        localTable.setFillParent(true);
        localTable.align(Align.topLeft);
//        localTable.padTop(GENERAL_HEIGHT_SPACING);
        localTable.padRight(TABLE_HORIZONTAL_PADDING);
        localTable.padLeft(TABLE_HORIZONTAL_PADDING);
        localTable.padBottom(TABLE_HORIZONTAL_PADDING);

        // Labels styles and settings
        Label.LabelStyle titleLabelStyle = new Label.LabelStyle();
        titleLabelStyle.font = new BitmapFont(Gdx.files.internal("default.fnt"));
        titleLabelStyle.font.getData().setScale(3f);

        Table headerTable = new Table();

        TextButton cancelButton = new TextButton("X", skin);
        cancelButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setVisible(false);
            }
        });

        headerTable.add().padRight(300).align(Align.left);
        headerTable.add(new Label("Your Info", titleLabelStyle)).padRight(240).align(Align.center);
        headerTable.add(cancelButton).prefWidth(BUTTON_WIDTH).prefHeight(BUTTON_HEIGHT).align(Align.right);

        headerTable.background(new TextureRegionDrawable(getTexture(Color.GRAY)));

        add(headerTable).align(Align.left).row();
        add().padTop(GENERAL_HEIGHT_SPACING).row();

        for (ProfileDataItem profileItem : profileData) {
            localTable.add(new Label(profileItem.getAttributeName() + ":", skin)).padRight(DIALOG_HORIZONTAL_SPACING).align(Align.left);
            localTable.add(new Label(profileItem.getAttributeValue(), skin)).prefWidth(TEXT_FIELD_WIDTH).align(Align.left).row();
            localTable.add().padTop(GENERAL_HEIGHT_SPACING).row();
        }



        ScrollPane scrollPane = new ScrollPane(localTable, skin);

        add(scrollPane).prefHeight(DIALOG_HEIGHT - BUTTON_HEIGHT * 3).prefWidth(DIALOG_WIDTH - TABLE_HORIZONTAL_PADDING * 2).align(Align.center).row();
        add().padTop(GENERAL_HEIGHT_SPACING).row();
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

    static public TextureRegion getTexture(Color color) {
        // Create a Pixmap with the White color
        Pixmap pixmapW = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmapW.setColor(color);
        pixmapW.fill();

        // You can draw more on the pixmap if needed
        pixmapW.setColor(0, 1, 0, 1); // Set color to green
        pixmapW.drawRectangle(10, 10, 50, 50); // Draw a green rectangle


        // Create a TextureRegion from the Pixmap
        Texture texture = new Texture(pixmapW);
        return new TextureRegion(texture);
    }
}
