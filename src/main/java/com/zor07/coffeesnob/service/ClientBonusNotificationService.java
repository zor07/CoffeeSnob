package com.zor07.coffeesnob.service;

import com.zor07.coffeesnob.entity.Client;
import com.zor07.coffeesnob.entity.enums.BonusPointsAction;
import com.zor07.coffeesnob.service.bot.MessageSender;
import org.springframework.stereotype.Service;

@Service
public class ClientBonusNotificationService {

    private final MessageSender messageSender;

    public ClientBonusNotificationService(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void notifyClient(Client client, BonusPointsAction action, int points) {
        String message = action == BonusPointsAction.USE
                ? String.format("Вы потратили %s баллов", points)
                : String.format("Вам начислено %s баллов", points);

        messageSender.sendMessage(message, client.getChatId());
    }
}
