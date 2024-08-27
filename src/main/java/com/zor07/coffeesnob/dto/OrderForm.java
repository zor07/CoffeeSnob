package com.zor07.coffeesnob.dto;

import com.zor07.coffeesnob.entity.enums.BonusPointsAction;

import java.math.BigDecimal;
import java.util.List;

public record OrderForm(
    UserDto user,
    ClientDto client,
    BonusPointsAction bonusPointsAction,
    List<OrderFormItem> items,
    BigDecimal totalAmount,
    BigDecimal discountAmount,
    BigDecimal finalAmount
) {



}
