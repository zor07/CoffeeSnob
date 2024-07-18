package com.zor07.coffeesnob.service.bot.handler.message;

import com.zor07.coffeesnob.service.bot.utils.KeyboardUtils;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
public class UnknownMessageHandler implements MessageHandler {

    private static final String MESSAGE_TEXT = """
            Я пока не знаю что на это ответить...
            Но возможно в будущем я вас пойму :)
            """;

    @Override
    public SendMessage handle(Long chatId) {
        return SendMessage
                .builder()
                .chatId(chatId)
                .text(MESSAGE_TEXT)
                .replyMarkup(KeyboardUtils.buildKeyboard())
                .build();
    }
}
