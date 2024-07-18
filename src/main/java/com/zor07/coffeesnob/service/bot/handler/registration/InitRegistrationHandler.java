package com.zor07.coffeesnob.service.bot.handler.registration;

import com.zor07.coffeesnob.entity.Client;
import com.zor07.coffeesnob.service.ClientService;
import com.zor07.coffeesnob.service.bot.enums.RegistrationState;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
public class InitRegistrationHandler implements RegistrationHandler {

    private static final String TEXT = "Давайте познакомимся, я кофе-бот, а вас как зовут?";
    private final ClientService clientService;

    public InitRegistrationHandler(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public SendMessage handle(String message, Long chatId, Client client) {
        client.setChatId(chatId);
        client.setRegistrationState(RegistrationState.ASK_NAME.name());
        clientService.create(client);
        return SendMessage
                .builder()
                .chatId(chatId)
                .text(TEXT)
                .build();

    }
}
