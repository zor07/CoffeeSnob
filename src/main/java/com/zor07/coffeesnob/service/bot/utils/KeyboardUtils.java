package com.zor07.coffeesnob.service.bot.utils;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;

public class KeyboardUtils {

    private static final String MY_BC_TEXT = "Моя бонусная карта";

    public static ReplyKeyboardMarkup buildKeyboard() {
        KeyboardRow row = new KeyboardRow(MY_BC_TEXT);
        return ReplyKeyboardMarkup
                .builder()
                .keyboard(List.of(row))
                .build();
    }

}
