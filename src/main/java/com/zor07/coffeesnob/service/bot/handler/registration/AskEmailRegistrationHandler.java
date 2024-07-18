package com.zor07.coffeesnob.service.bot.handler.registration;

import com.zor07.coffeesnob.entity.Client;
import com.zor07.coffeesnob.service.ClientService;
import com.zor07.coffeesnob.service.bot.enums.RegistrationState;
import com.zor07.coffeesnob.service.bot.utils.KeyboardUtils;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AskEmailRegistrationHandler implements RegistrationHandler {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final String EMAIL_SUCCESS = """
            Спасибо за регистрацию!
            Вы можете посмотреть количество баллов нажав на кнопку: "Моя бонусная карта"
            """;
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
            client.setRegistrationState(RegistrationState.REGISTERED.name());
            clientService.update(client);
            return SendMessage.builder()
                    .chatId(chatId)
                    .text(EMAIL_SUCCESS)
                    .replyMarkup(KeyboardUtils.buildKeyboard())
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
