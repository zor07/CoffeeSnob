package com.zor07.coffeesnob.dto;

import java.math.BigDecimal;

public record OrderFormItem(
        Long menuItemId,
        BigDecimal quantity
) {
}
