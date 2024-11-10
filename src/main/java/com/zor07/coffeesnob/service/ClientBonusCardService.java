package com.zor07.coffeesnob.service;

import com.zor07.coffeesnob.entity.ClientBonusCard;
import com.zor07.coffeesnob.exception.EntityNotFoundException;
import com.zor07.coffeesnob.repository.ClientBonusCardRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientBonusCardService {

    private final ClientBonusCardRepository clientBonusCardRepository;

    public ClientBonusCardService(ClientBonusCardRepository clientBonusCardRepository) {
        this.clientBonusCardRepository = clientBonusCardRepository;
    }

    public ClientBonusCard save(ClientBonusCard clientBonusCard) {
        return clientBonusCardRepository.save(clientBonusCard);
    }

    public ClientBonusCard findByChatId(Long chatId) {
        return clientBonusCardRepository.findByChatId(chatId)
                .orElse(new ClientBonusCard());
    }

    public ClientBonusCard findByPhone(String phone) {
        return clientBonusCardRepository.findByPhone(phone)
                .orElseThrow(() -> new EntityNotFoundException("Bonus card for client with phone: " + phone + " not found"));
    }
}
