package ru.geekbrains.service;

import ru.geekbrains.controller.dto.OrderDto;
import ru.geekbrains.persist.Order;
import ru.geekbrains.service.dto.OrderMessage;

import java.util.List;

public interface OrderService {

    List<OrderDto> findOrdersByUsername(String username);

    void createOrder(String username);


}
