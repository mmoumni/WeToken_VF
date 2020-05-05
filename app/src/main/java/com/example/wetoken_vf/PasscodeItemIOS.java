package com.example.wetoken_vf;

import com.nirigo.mobile.view.passcode.models.PasscodeItem;


public class PasscodeItemIOS extends PasscodeItem{

    private String characters;

    public PasscodeItemIOS(String value, int type, String characters) {
        super(value, type);
        this.characters = characters;
    }

    public String getCharacters() {
        return characters;
    }
}