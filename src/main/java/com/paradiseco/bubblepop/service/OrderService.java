package com.paradiseco.bubblepop.service;

import com.paradiseco.bubblepop.entity.DrinkEntity;
import com.paradiseco.bubblepop.entity.OrderEntity;
import com.paradiseco.bubblepop.model.OrderModel;
import com.paradiseco.bubblepop.repository.DrinkRepo;
import com.paradiseco.bubblepop.repository.OrderRepo;
import com.paradiseco.bubblepop.repository.UserRepo;
import jakarta.persistence.criteria.Order;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private DrinkRepo drinkRepo;
    @Autowired
    private UserRepo userRepo;

    public OrderEntity createOrder(String name, List<String> drinkIds) throws NoSuchElementException{
        OrderEntity newOrder = new OrderEntity();
        newOrder.setName(name);
        List<DrinkEntity> drinkList = new ArrayList<>();
        for(String stringId : drinkIds){
            drinkList.add(drinkRepo.findById(Long.valueOf(stringId)).get());
        }
        newOrder.setDrinks(drinkList);
        return orderRepo.save(newOrder);
    }

    public OrderEntity assignBarista(Long orderId, Long baristaId) throws NoSuchElementException {
        OrderEntity order = orderRepo.findById(orderId).get();
        order.setUser(userRepo.findById(baristaId).get());
        orderRepo.save(order);
        return order;
    }

    public Long removeOrder(Long orderId){
        orderRepo.deleteById(orderId);
        return orderId;
    }

    public List<OrderModel> getAllEmptyOrders(){
        return orderRepo.findAllByUserNull().stream().map(orderEntity -> OrderModel.toModel(orderEntity)).collect(Collectors.toList());
    }

    public List<OrderModel> getAllOrdersOfBarista(Long baristaId){
        return orderRepo.findAllByUser(userRepo.findById(baristaId).get()).stream().map(orderEntity -> OrderModel.toModel(orderEntity)).collect(Collectors.toList());
    }

    public List<OrderModel> getAllOrders(){
        return orderRepo.findAll().stream().map(orderEntity -> OrderModel.toModel(orderEntity)).collect(Collectors.toList());
    }
}
