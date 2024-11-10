package com.zor07.coffeesnob.service;

import com.zor07.coffeesnob.dto.ClientDto;
import com.zor07.coffeesnob.dto.OrderForm;
import com.zor07.coffeesnob.dto.OrderFormItem;
import com.zor07.coffeesnob.entity.Client;
import com.zor07.coffeesnob.entity.MenuItem;
import com.zor07.coffeesnob.entity.Order;
import com.zor07.coffeesnob.entity.OrderItem;
import com.zor07.coffeesnob.entity.User;
import com.zor07.coffeesnob.repository.OrderItemRepository;
import com.zor07.coffeesnob.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final MenuItemService menuItemService;
    private final OrderItemRepository orderItemRepository;
    private final ClientBonusCardTransactionService clientBonusCardTransactionService;

    public OrderService(OrderRepository orderRepository,
                        MenuItemService menuItemService,
                        OrderItemRepository orderItemRepository,
                        ClientBonusCardTransactionService clientBonusCardTransactionService) {
        this.orderRepository = orderRepository;
        this.menuItemService = menuItemService;
        this.orderItemRepository = orderItemRepository;
        this.clientBonusCardTransactionService = clientBonusCardTransactionService;
    }

    @Transactional
    public void saveOrder(OrderForm orderForm) {
        // Создание и установка данных заказа
        Order order = new Order();
        Client client = Optional.ofNullable(orderForm.client())
                .map(ClientDto::toEntity)
                .orElse(null);
        User user = orderForm.user().toEntity();

        order.setClient(client);
        order.setUser(user);
        order.setBonusPointsAction(orderForm.bonusPointsAction());
        order.setTotalAmount(orderForm.totalAmount());
        order.setDiscountAmount(orderForm.discountAmount());
        order.setFinalAmount(orderForm.finalAmount());
        order.setCreatedAt(LocalDateTime.now());

        // Сохранение заказа для получения ID
        Order savedOrder = orderRepository.save(order);

        // Получение списка ID позиций меню
        List<Long> menuItemIds = orderForm.items()
                .stream()
                .map(OrderFormItem::menuItemId)
                .toList();

        // Получение списка меню
        Map<Long, MenuItem> menuItemMap = menuItemService.findByIds(menuItemIds)
                .stream()
                .collect(Collectors.toMap(MenuItem::getId, Function.identity()));

        // Создание и сохранение позиций заказа
        Set<OrderItem> orderItems = orderForm.items()
                .stream()
                .map(orderFormItem -> {
                    MenuItem menuItem = menuItemMap.get(orderFormItem.menuItemId());
                    OrderItem orderItem = new OrderItem();
                    orderItem.setMenuItem(menuItem);
                    orderItem.setOrder(savedOrder);
                    orderItem.setPrice(menuItem.getPrice());
                    orderItem.setQuantity(orderFormItem.quantity());
                    return orderItem;
                })
                .collect(Collectors.toSet());

        // Сохранение позиций заказа
        orderItemRepository.saveAll(orderItems);

        // Обновление заказа с позициями заказа
        savedOrder.setOrderItems(orderItems);
        orderRepository.save(savedOrder);

        // Создание транзакции для бонусной карты
        if (savedOrder.getClient() != null) {
            clientBonusCardTransactionService.createTransactionFromOrder(savedOrder);
        }
    }


}
