package com.paradiseco.bubblepop.model;

import com.paradiseco.bubblepop.entity.OrderEntity;

import java.util.List;
import java.util.stream.Collectors;

public class OrderModel {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String name;
    private List<DrinkModel> drinks;

    public static OrderModel toModel(OrderEntity entity){
        OrderModel model = new OrderModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setDrinks(entity.getDrinks().stream()
                .map(drinkEntity -> DrinkModel.toModel(drinkEntity))
                .collect(Collectors.toList()));
        return model;
    }

    public List<DrinkModel> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<DrinkModel> drinks) {
        this.drinks = drinks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
