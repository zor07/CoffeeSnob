package com.zor07.coffeesnob.service.bot.handler.registration;

import com.zor07.coffeesnob.entity.Client;
import com.zor07.coffeesnob.service.ClientService;
import com.zor07.coffeesnob.service.bot.enums.RegistrationState;
import com.zor07.coffeesnob.service.bot.utils.KeyboardUtils;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
public class AskPhoneRegistrationHandler implements RegistrationHandler {

    private static final String PHONE_SUCCESS = """
            Спасибо за регистрацию!
            Вы можете посмотреть количество баллов нажав на кнопку: "Моя бонусная карта"
            """;
    private static final String PHONE_NULL = "Номер телефона не может быть null";
    private static final String PHONE_LENGTH_ERROR = "Номер телефона не может быть короче 5 символов";
    private static final String PHONE_ERROR = "То, что ты ввел не очень похоже на номер телефона( Давай еще разок...";


    private final ClientService clientService;

    public AskPhoneRegistrationHandler(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public SendMessage handle(String rawPhoneNumber, Long chatId, Client client) {
        try {
            String phoneNumber = cleanPhoneNumber(rawPhoneNumber);
            client.setPhone(phoneNumber);
            client.setRegistrationState(RegistrationState.REGISTERED.name());
            clientService.update(client);
            return SendMessage.builder()
                    .chatId(chatId)
                    .text(PHONE_SUCCESS)
                    .replyMarkup(KeyboardUtils.buildKeyboard())
                    .build();
        } catch (Exception e) {
            return SendMessage.builder()
                    .chatId(chatId)
                    .text(PHONE_ERROR)
                    .build();
        }
    }

    private String cleanPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            throw new IllegalArgumentException(PHONE_NULL);
        }

        // Удаление всех символов, кроме цифр
        String cleanedNumber = phoneNumber.replaceAll("\\D", "");

        // Проверка длины очищенного номера
        if (cleanedNumber.length() < 5) {
            throw new IllegalArgumentException(PHONE_LENGTH_ERROR);
        }

        return cleanedNumber;
    }
}
