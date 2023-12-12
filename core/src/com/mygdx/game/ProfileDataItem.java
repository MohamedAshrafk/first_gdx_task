package com.mygdx.game;

public class ProfileDataItem {
    public String getAttributeName() {
        return attributeName;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    private final String attributeName;
    private final String attributeValue;

    ProfileDataItem(String attributeName, String attributeValue) {
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
    }
}
