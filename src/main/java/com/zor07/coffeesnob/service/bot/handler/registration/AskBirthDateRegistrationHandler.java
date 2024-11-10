package com.zor07.coffeesnob.service.bot.handler.registration;

import com.zor07.coffeesnob.entity.Client;
import com.zor07.coffeesnob.service.ClientService;
import com.zor07.coffeesnob.service.bot.enums.RegistrationState;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
public class AskBirthDateRegistrationHandler implements RegistrationHandler {

    private static final String ASK_EMAIL_TEXT = """
            Круто! Осталось ввести только email ;)
            """;
    private static final String BIRTHDATE_ERROR_TEXT = """
            Воу... Чет не очень похоже на дату...Попробуйте еще раз в формате DD.MM.YYYY
            """;

    private final ClientService clientService;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public AskBirthDateRegistrationHandler(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public SendMessage handle(String dateString, Long chatId, Client client) {
        try {
            LocalDate birthDate = LocalDate.parse(dateString, formatter);
            client.setBirthday(birthDate);
            client.setRegistrationState(RegistrationState.ASK_EMAIL.name());
            clientService.update(client);
            return SendMessage.builder()
                    .chatId(chatId)
                    .text(ASK_EMAIL_TEXT)
                    .build();
        } catch (DateTimeParseException e) {
            return SendMessage
                    .builder()
                    .chatId(chatId)
                    .text(BIRTHDATE_ERROR_TEXT)
                    .build();
        }
    }
}
