package com.zor07.coffeesnob.controller;

import com.zor07.coffeesnob.dto.MenuItemDto;
import com.zor07.coffeesnob.entity.User;
import com.zor07.coffeesnob.service.MenuItemService;
import com.zor07.coffeesnob.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final UserService userService;
    private final MenuItemService menuItemService;


    public OrderController(UserService userService,
                           MenuItemService menuItemService) {
        this.userService = userService;
        this.menuItemService = menuItemService;
    }

    @GetMapping
    public String newOrder(Model model, Principal principal) {
        String username = principal.getName();
        User user = (User) userService.loadUserByUsername(username);
        List<MenuItemDto> menuItems = menuItemService.findAll()
                .stream()
                .map(MenuItemDto::toDto)
                .toList();

        model.addAttribute("menuItems", menuItems);
        model.addAttribute("user", user);

        return "order/form";
    }
}
