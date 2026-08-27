package com.cafe.cafeBackend.service;

import com.cafe.cafeBackend.dto.OrderRequest;
import com.cafe.cafeBackend.dto.OrderResponse;
import com.cafe.cafeBackend.model.MenuItem;
import com.cafe.cafeBackend.model.Order;
import com.cafe.cafeBackend.repository.MenuItemRepository;
import com.cafe.cafeBackend.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MenuItemRepository menuItemRepository;

    public OrderResponse createOrder(OrderRequest request, String userEmail) {

        List<Long> itemIds = request.items().stream().map(Long::valueOf).toList();
        List<MenuItem> items = menuItemRepository.findAllById(itemIds);

        if (items.size() != itemIds.size()) {
            throw new IllegalStateException("One or more menu items not found");
        }

        double realTotal = items.stream().mapToDouble(MenuItem::getPrice).sum();

        if (Math.abs(realTotal - request.total()) > 0.01) {
            throw new IllegalStateException("Order total does not match menu prices");
        }

        Order order = Order.builder()
                .time(request.time())
                .name(request.name())
                .items(items)
                .total(realTotal)
                .userEmail(userEmail)
                .confirmedAt(LocalDateTime.now())
                .build();

        orderRepository.save(order);

        return new OrderResponse(String.valueOf(order.getId()), order.getConfirmedAt().toString());
    }
}