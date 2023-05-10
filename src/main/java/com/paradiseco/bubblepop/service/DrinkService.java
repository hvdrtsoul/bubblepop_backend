package com.paradiseco.bubblepop.service;

import com.paradiseco.bubblepop.entity.DrinkEntity;
import com.paradiseco.bubblepop.repository.DrinkRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class DrinkService {
    @Autowired
    private DrinkRepo drinkRepo;

    public DrinkEntity addDrink(DrinkEntity drink){
        return drinkRepo.save(drink);
    }

    public Long removeDrink(Long id){
        drinkRepo.deleteById(id);
        return id;
    }

    public Long setDrinkPrice(Long id, Integer newPrice) throws NoSuchElementException {
        DrinkEntity drinkEntity = drinkRepo.findById(id).get();
        drinkEntity.setPrice(newPrice);
        drinkRepo.save(drinkEntity);
        return id;
    }

    public List<DrinkEntity> getAllDrinks(){
        return drinkRepo.findAll();
    }
}
