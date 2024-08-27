package com.zor07.coffeesnob.service;

import com.zor07.coffeesnob.entity.ClientBonusCard;
import com.zor07.coffeesnob.entity.ClientBonusCardTransaction;
import com.zor07.coffeesnob.entity.Order;
import com.zor07.coffeesnob.entity.enums.TransactionType;
import com.zor07.coffeesnob.exception.EntityNotFoundException;
import com.zor07.coffeesnob.repository.ClientBonusCardRepository;
import com.zor07.coffeesnob.repository.ClientBonusCardTransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class ClientBonusCardTransactionService {

    private final ClientBonusCardTransactionRepository transactionRepository;
    private final ClientBonusCardRepository clientBonusCardRepository;
    private final ClientBonusNotificationService clientBonusNotificationService;

    public ClientBonusCardTransactionService(ClientBonusCardTransactionRepository transactionRepository,
                                             ClientBonusCardRepository clientBonusCardRepository,
                                             ClientBonusNotificationService clientBonusNotificationService) {
        this.transactionRepository = transactionRepository;
        this.clientBonusCardRepository = clientBonusCardRepository;
        this.clientBonusNotificationService = clientBonusNotificationService;
    }

    @Transactional
    public void createTransactionFromOrder(Order order) {
        Long clientId = order.getClient().getId();
        ClientBonusCard bonusCard = clientBonusCardRepository.findByClientId(clientId)
                .orElseThrow(() -> new EntityNotFoundException("Bonus card not found for client ID: " + clientId));

        TransactionType transactionType;
        int points;
        switch (order.getBonusPointsAction()) {
            case EARN:
                transactionType = TransactionType.DEBIT;
                BigDecimal totalAmount = order.getOrderItems().stream()
                        .map(item -> item.getPrice().multiply(item.getQuantity()))
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

                points = totalAmount.multiply(BigDecimal.valueOf(bonusCard.getDiscountPercent()))
                        .divide(BigDecimal.valueOf(100)).intValue();
                bonusCard.setAmount(bonusCard.getAmount() + points);
                break;

            case USE:
                transactionType = TransactionType.CREDIT;
                points = bonusCard.getAmount();
                bonusCard.setAmount(0); // Assuming we're using all points
                break;

            default:
                throw new IllegalArgumentException("Invalid bonus points action");
        }

        clientBonusCardRepository.save(bonusCard);

        ClientBonusCardTransaction transaction = new ClientBonusCardTransaction();
        transaction.setOrder(order);
        transaction.setTransactionType(transactionType);
        transaction.setPoints(points);
        transaction.setTransactionDate(LocalDateTime.now());

        transactionRepository.save(transaction);
        clientBonusNotificationService.notifyClient(order.getClient(), order.getBonusPointsAction(), points);
    }
}