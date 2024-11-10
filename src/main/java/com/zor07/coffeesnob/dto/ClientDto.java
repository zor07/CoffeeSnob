package com.zor07.coffeesnob.dto;

import com.zor07.coffeesnob.entity.Client;

import java.time.LocalDate;

public record ClientDto(
        Long id,
        String name,
        String email,
        String phone,
        LocalDate birthday,
        Long chatId,
        String registrationState
) {

    public static ClientDto toDto(Client client) {
        return new ClientDto(
                client.getId(),
                client.getName(),
                client.getEmail(),
                client.getPhone(),
                client.getBirthday(),
                client.getChatId(),
                client.getRegistrationState()
        );
    }

    public static ClientDto empty() {
        return new ClientDto(null, null,null,null,null,null,null);
    }

    public Client toEntity() {
        return new Client(id, name, email, phone, birthday, chatId, registrationState);
    }




}
