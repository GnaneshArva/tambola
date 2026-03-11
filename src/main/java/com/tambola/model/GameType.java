package com.tambola.model;

import lombok.Getter;

@Getter
public enum GameType {

    TOP_LINE("Top Line"),
    MIDDLE_LINE("Middle Line"),
    BOTTOM_LINE("Bottom Line"),
    FULL_HOUSE("Full House"),
    EARLY_FIVE("Early Five");

    private final String displayName;

    GameType(String displayName) {
        this.displayName = displayName;
    }
}
