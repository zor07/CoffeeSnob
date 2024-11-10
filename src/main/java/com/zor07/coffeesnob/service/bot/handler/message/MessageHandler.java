package com.zor07.coffeesnob.service.bot.handler.message;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface MessageHandler {

    SendMessage handle(Long chatId);

}
