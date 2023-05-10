package com.paradiseco.bubblepop.model;

import com.paradiseco.bubblepop.entity.DrinkEntity;

public class DrinkModel {
    private String name;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static DrinkModel toModel(DrinkEntity entity){
        DrinkModel model = new DrinkModel();
        model.setName(entity.getName());
        model.setId(entity.getId());
        return model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
