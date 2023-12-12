package com.mygdx.game;

import java.util.List;

public class ProfileData{
    final java.util.List<ProfileDataItem> profileDataList;


    public ProfileData(List<ProfileDataItem> profileDataList) {
        this.profileDataList = profileDataList;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (ProfileDataItem profileDataItem: profileDataList){
            stringBuilder.append(profileDataItem.getAttributeName()).append(":  ").append(profileDataItem.getAttributeValue()).append("\n").append("\n");
        }
        return stringBuilder.toString();
    }
}

class ProfileDataItem {
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