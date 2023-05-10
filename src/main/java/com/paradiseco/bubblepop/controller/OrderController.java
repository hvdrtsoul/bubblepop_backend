package com.paradiseco.bubblepop.controller;


import com.paradiseco.bubblepop.entity.DrinkEntity;
import com.paradiseco.bubblepop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/add")
    public ResponseEntity addOrder(@RequestParam String name, @RequestParam List<String> drinkIds){
        try {
            orderService.createOrder(name, drinkIds);
            return ResponseEntity.ok("OK");
        }
        catch(NoSuchElementException e){
            return ResponseEntity.badRequest().body("No such element..." + e.getLocalizedMessage());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Something went wrong..." + e.getLocalizedMessage());
        }
    }

    @PostMapping("/assign")
    @Secured("BARISTA")
    public ResponseEntity assignBarista(@RequestParam Long orderId, @RequestParam Long baristaId){
        try {
            orderService.assignBarista(orderId, baristaId);
            return ResponseEntity.ok("OK");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Something went wrong...");
        }
    }

    @PostMapping("/remove")
    @Secured("BARISTA")
    public ResponseEntity removeOrder(@RequestParam Long orderId){
        try {
            return ResponseEntity.ok(orderService.removeOrder(orderId));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Something went wrong...");
        }
    }

    @GetMapping("/{baristaId}")
    @Secured("BARISTA")
    public ResponseEntity getOrdersOfBarista(@PathVariable Long baristaId){
        try {
            return ResponseEntity.ok(orderService.getAllOrdersOfBarista(baristaId));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Something went wrong...");
        }
    }

    @GetMapping
    @Secured("BARISTA")
    public ResponseEntity getFreeOrders(){
        try {
            return ResponseEntity.ok(orderService.getAllEmptyOrders());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Something went wrong...");
        }
    }

    @GetMapping("/all")
    public ResponseEntity getAllOrders(){
        try {
            return ResponseEntity.ok(orderService.getAllOrders());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Something went wrong...");
        }
    }
}
