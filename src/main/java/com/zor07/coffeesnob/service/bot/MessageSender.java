package com.zor07.coffeesnob.service.bot;

import com.zor07.coffeesnob.service.bot.utils.KeyboardUtils;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Service
public class MessageSender {

    private final TelegramClient telegramClient;

    public MessageSender(TelegramClient telegramClient) {
        this.telegramClient = telegramClient;
    }

    public void sendMessage(SendMessage message) {
        try {
            telegramClient.execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(String message, Long chatId) {
        SendMessage sendMessage = SendMessage
                .builder()
                .chatId(chatId)
                .text(message)
                .replyMarkup(KeyboardUtils.buildKeyboard())
                .build();

        try {
            telegramClient.execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
