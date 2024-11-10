package com.zor07.coffeesnob.service.bot.enums;

import java.util.Arrays;

public enum MessageType {
    MY_BONUS_CARD("Моя бонусная карта"),
    START("/start"),
    UNKNOWN("");

    private final String text;

    MessageType(String text) {
        this.text = text;
    }

    public static MessageType fromMessageText(String text) {
        return Arrays.stream(values())
                .filter(type -> type.getText().equals(text))
                .findFirst()
                .orElse(UNKNOWN);
    }

    public String getText() {
        return text;
    }
}
