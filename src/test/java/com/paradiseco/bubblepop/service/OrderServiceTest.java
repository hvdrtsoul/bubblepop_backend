package com.paradiseco.bubblepop.service;

import com.paradiseco.bubblepop.entity.DrinkEntity;
import com.paradiseco.bubblepop.entity.OrderEntity;
import com.paradiseco.bubblepop.entity.UserEntity;
import com.paradiseco.bubblepop.model.OrderModel;
import com.paradiseco.bubblepop.repository.DrinkRepo;
import com.paradiseco.bubblepop.repository.OrderRepo;
import com.paradiseco.bubblepop.repository.UserRepo;
import com.paradiseco.bubblepop.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Mock
    private OrderRepo orderRepo;

    @Mock
    private DrinkRepo drinkRepo;

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createOrder_shouldCreateNewOrder() {
        String name = "Order 1";
        List<String> drinkIds = List.of("1", "2", "3");

        DrinkEntity drink1 = new DrinkEntity();
        drink1.setId(1L);
        drink1.setName("Drink 1");

        DrinkEntity drink2 = new DrinkEntity();
        drink2.setId(2L);
        drink2.setName("Drink 2");

        DrinkEntity drink3 = new DrinkEntity();
        drink3.setId(3L);
        drink3.setName("Drink 3");

        when(drinkRepo.findById(1L)).thenReturn(Optional.of(drink1));
        when(drinkRepo.findById(2L)).thenReturn(Optional.of(drink2));
        when(drinkRepo.findById(3L)).thenReturn(Optional.of(drink3));

        OrderEntity savedOrder = new OrderEntity();
        savedOrder.setId(1L);
        savedOrder.setName(name);
        savedOrder.setDrinks(List.of(drink1, drink2, drink3));

        when(orderRepo.save(any(OrderEntity.class))).thenReturn(savedOrder);

        OrderEntity createdOrder = orderService.createOrder(name, drinkIds);

        assertNotNull(createdOrder);
        assertEquals(name, createdOrder.getName());
        assertEquals(3, createdOrder.getDrinks().size());
        assertEquals("Drink 1", createdOrder.getDrinks().get(0).getName());
        assertEquals("Drink 2", createdOrder.getDrinks().get(1).getName());
        assertEquals("Drink 3", createdOrder.getDrinks().get(2).getName());

        verify(drinkRepo, times(3)).findById(anyLong());
        verify(orderRepo, times(1)).save(any(OrderEntity.class));
    }

    @Test
    void createOrder_shouldThrowNoSuchElementExceptionWhenDrinkNotFound() {
        String name = "Order 1";
        List<String> drinkIds = List.of("1", "2", "3");

        when(drinkRepo.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> orderService.createOrder(name, drinkIds));

        verify(drinkRepo, times(1)).findById(anyLong());
        verify(orderRepo, never()).save(any(OrderEntity.class));
    }

    // Add more test cases for other methods in OrderService

}
