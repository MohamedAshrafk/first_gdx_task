package com.mygdx.game;

/** Model for setting and getting every Profile attribute of the user
 * (Can be replaced by a {@link java.util.Map})
 * */
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
