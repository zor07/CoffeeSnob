package com.zor07.coffeesnob.config;

import com.zor07.coffeesnob.service.bot.CoffeeDemoBot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Configuration
public class TgBotConfig {

    @Value("${telegram.bot.name}")
    private String name;

    @Value("${telegram.bot.token}")
    private String token;

    @Bean
    public TelegramBotsLongPollingApplication tgBotApp(CoffeeDemoBot reminderBot) throws TelegramApiException {
        TelegramBotsLongPollingApplication botsApi = new TelegramBotsLongPollingApplication();
        botsApi.registerBot(token, reminderBot);
        return botsApi;
    }

    @Bean
    public TelegramClient telegramClient() {
        return new OkHttpTelegramClient(token);
    }
}
