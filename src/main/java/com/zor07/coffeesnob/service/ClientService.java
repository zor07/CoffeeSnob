package com.zor07.coffeesnob.service;

import com.zor07.coffeesnob.entity.Client;
import com.zor07.coffeesnob.entity.ClientBonusCard;
import com.zor07.coffeesnob.repository.ClientRepository;
import com.zor07.coffeesnob.service.bot.enums.RegistrationState;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientBonusCardService clientBonusCardService;

    public ClientService(ClientRepository clientRepository,
                         ClientBonusCardService clientBonusCardService) {
        this.clientRepository = clientRepository;
        this.clientBonusCardService = clientBonusCardService;
    }

    public Client getByChatId(Long chatId) {
        return clientRepository.findByChatId(chatId)
                .orElse(new Client(RegistrationState.INIT.name()));
    }

    @Transactional
    public void create(Client client) {
        Client savedClient = clientRepository.save(client);
        ClientBonusCard clientBonusCard = new ClientBonusCard();
        clientBonusCard.setClient(savedClient);
        clientBonusCardService.save(clientBonusCard);
    }

    public void update(Client client) {
        clientRepository.save(client);
    }
}
