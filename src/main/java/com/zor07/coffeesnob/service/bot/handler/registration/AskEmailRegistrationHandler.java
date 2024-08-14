package com.zor07.coffeesnob.service.bot.handler.registration;

import com.zor07.coffeesnob.entity.Client;
import com.zor07.coffeesnob.service.ClientService;
import com.zor07.coffeesnob.service.bot.enums.RegistrationState;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AskEmailRegistrationHandler implements RegistrationHandler {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final String EMAIL_SUCCESS = "Отлично! Теперь введи номер телефона, пожалуйста";
    private static final String EMAIL_ERROR_TEXT = """
            То, что вы ввели не очень похоже на имейл. Давайте попробуем еще раз :\\)
            """;

    private final ClientService clientService;

    public AskEmailRegistrationHandler(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public SendMessage handle(String email, Long chatId, Client client) {
        if (isValidEmail(email)) {
            client.setEmail(email);
            client.setRegistrationState(RegistrationState.ASK_PHONE.name());
            clientService.update(client);
            return SendMessage.builder()
                    .chatId(chatId)
                    .text(EMAIL_SUCCESS)
                    .build();
        } else {
            return SendMessage.builder()
                    .chatId(chatId)
                    .text(EMAIL_ERROR_TEXT)
                    .build();
        }
    }

    private boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
