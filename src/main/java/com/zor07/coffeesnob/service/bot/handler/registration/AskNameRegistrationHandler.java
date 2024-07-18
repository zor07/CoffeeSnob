package com.zor07.coffeesnob.service.bot.handler.registration;

import com.zor07.coffeesnob.entity.Client;
import com.zor07.coffeesnob.service.ClientService;
import com.zor07.coffeesnob.service.bot.enums.RegistrationState;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
public class AskNameRegistrationHandler implements RegistrationHandler {

    private static final String ASK_BIRTHDATE_TEXT = """
            Приятно познакомиться! Введите плз дату рождения в формате DD.MM.YYYY
            """;

    private final ClientService clientService;

    public AskNameRegistrationHandler(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public SendMessage handle(String name, Long chatId, Client client) {
        client.setName(name);
        client.setRegistrationState(RegistrationState.ASK_BIRTHDATE.name());
        clientService.update(client);

        return SendMessage
                .builder()
                .chatId(chatId)
                .text(ASK_BIRTHDATE_TEXT)
                .build();

    }
}
