package com.mygdx.game;

// Helper method to create a rounded drawable

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class DrawingUtilities {
    // Helper method to create a rounded corner window
    static public Drawable createRoundedDrawable(Color color, int width, int height, int radius) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fillCircle(radius, radius, radius);
        pixmap.fillCircle(width - radius, radius, radius);
        pixmap.fillCircle(width - radius, height - radius, radius);
        pixmap.fillCircle(radius, height - radius, radius);
        pixmap.fillRectangle(0, radius, width, height - (radius * 2));

        pixmap.fillRectangle(radius, 0, width - (radius * 2), height);
        Texture pixmaptex = new Texture(pixmap);
        pixmap.dispose();

        return new TextureRegionDrawable(pixmaptex);
    }

    static public Drawable createLineBorder(Color color, int width, int height, int offsetX, int offsetY, int lineWidth, int lineHeight) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fillRectangle(offsetX, offsetY, lineWidth, lineHeight);

        Texture pixmaptex = new Texture(pixmap);
        pixmap.dispose();
        return new TextureRegionDrawable(pixmaptex);
    }

    // Helper method to create a circular drawable
    static public Drawable createCircularDrawable(Color color, int radius) {
        Pixmap pixmap = new Pixmap(2 * radius, 2 * radius, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fillCircle(radius, radius, radius);

        Texture texture = new Texture(pixmap);
        pixmap.dispose();

        return new TextureRegionDrawable(texture);
    }
}