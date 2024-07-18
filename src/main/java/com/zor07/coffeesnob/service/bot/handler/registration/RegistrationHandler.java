package com.zor07.coffeesnob.service.bot.handler.registration;

import com.zor07.coffeesnob.entity.Client;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface RegistrationHandler {

    SendMessage handle(String message, Long chatId, Client client);
}
