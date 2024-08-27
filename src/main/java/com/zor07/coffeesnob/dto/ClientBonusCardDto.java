package com.zor07.coffeesnob.dto;


import com.zor07.coffeesnob.entity.ClientBonusCard;

import java.util.Optional;

public record ClientBonusCardDto(
        Long id,
        ClientDto client,
        Integer amount,
        Integer discountPercent
) {

    public static ClientBonusCardDto toDto(ClientBonusCard card) {
        ClientDto client = Optional.ofNullable(card.getClient())
                .map(ClientDto::toDto)
                .orElse(ClientDto.empty());

        return new ClientBonusCardDto(
                card.getId(),
                client,
                card.getAmount(),
                card.getDiscountPercent()
        );
    }

}
