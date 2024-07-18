package com.zor07.coffeesnob.service.bot.handler.callback;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class CallbackHandler {

    public SendMessage handle(Update update) {
        Long chatId = update.getCallbackQuery().getMessage().getChatId();

        return  SendMessage
                .builder()
                .chatId(chatId)
                .text("У нас пока нет колбеков. Это задел на будущее")
                .build();
    }
}
