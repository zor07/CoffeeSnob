package com.zor07.coffeesnob.controller.api;

import com.zor07.coffeesnob.dto.ClientBonusCardDto;
import com.zor07.coffeesnob.entity.ClientBonusCard;
import com.zor07.coffeesnob.service.ClientBonusCardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clientBonusCard")
public class ClientBonusCardController {

    private final ClientBonusCardService clientBonusCardService;

    public ClientBonusCardController(ClientBonusCardService clientBonusCardService) {
        this.clientBonusCardService = clientBonusCardService;
    }

    @GetMapping
    public ClientBonusCardDto getByPhone(@RequestParam String phone)  {
        ClientBonusCard clientBonusCard = clientBonusCardService.findByPhone(phone);
        return ClientBonusCardDto.toDto(clientBonusCard);
    }

}
