package com.zor07.coffeesnob.service.bot.handler.registration;

import com.zor07.coffeesnob.entity.Client;
import com.zor07.coffeesnob.service.bot.enums.RegistrationState;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.HashMap;
import java.util.Map;

@Service
public class ClientRegistrationHandler {

    private final Map<RegistrationState, RegistrationHandler> registrationHandlers = new HashMap<>();

    public ClientRegistrationHandler(InitRegistrationHandler initRegistrationHandler,
                                     AskNameRegistrationHandler askNameRegistrationHandler,
                                     AskBirthDateRegistrationHandler askBirthDateRegistrationHandler,
                                     AskEmailRegistrationHandler askEmailRegistrationHandler) {

        registrationHandlers.put(RegistrationState.INIT, initRegistrationHandler);
        registrationHandlers.put(RegistrationState.ASK_NAME, askNameRegistrationHandler);
        registrationHandlers.put(RegistrationState.ASK_BIRTHDATE, askBirthDateRegistrationHandler);
        registrationHandlers.put(RegistrationState.ASK_EMAIL, askEmailRegistrationHandler);
    }

    public SendMessage register(String message, Long chatId, Client client) {
        RegistrationState registrationState = RegistrationState.valueOf(client.getRegistrationState());
        RegistrationHandler registrationHandler = registrationHandlers.get(registrationState);
        return registrationHandler.handle(message, chatId, client);
    }
}
