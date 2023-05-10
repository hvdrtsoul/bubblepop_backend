package com.paradiseco.bubblepop.controller;

import com.paradiseco.bubblepop.entity.DrinkEntity;
import com.paradiseco.bubblepop.entity.UserEntity;
import com.paradiseco.bubblepop.exception.UserAlreadyExistsException;
import com.paradiseco.bubblepop.exception.UserNotFoundException;
import com.paradiseco.bubblepop.service.DrinkService;
import com.paradiseco.bubblepop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/drinks")
public class DrinkController {
    @Autowired
    private DrinkService drinkService;

    @PostMapping("/add")
    @Secured("ADMIN")
    public ResponseEntity addDrink(@RequestBody DrinkEntity drink){
        try {
            drinkService.addDrink(drink);
            return ResponseEntity.ok("OK");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Something went wrong..." + e.getLocalizedMessage());
        }
    }

    @PostMapping("/remove")
    @Secured("ADMIN")
    public ResponseEntity removeDrink(@RequestParam Long id){
        try {
            drinkService.removeDrink(id);
            return ResponseEntity.ok("OK");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Something went wrong...");
        }
    }

    @PostMapping("/setprice")
    @Secured("ADMIN")
    public ResponseEntity setDrinkPrice(@RequestParam Long id, @RequestParam Integer price){
        try {
            drinkService.setDrinkPrice(id, price);
            return ResponseEntity.ok("OK");
        }
        catch(NoSuchElementException e){
            return ResponseEntity.badRequest().body("Drink not found");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Something went wrong...");
        }
    }

    @GetMapping
    public ResponseEntity getAllDrinks(){
        try{
            return ResponseEntity.ok().body(drinkService.getAllDrinks());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Something went wrong...");
        }
    }
}
