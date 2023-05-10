package com.paradiseco.bubblepop.repository;
import com.paradiseco.bubblepop.entity.DrinkEntity;
import com.paradiseco.bubblepop.repository.DrinkRepo;
import com.paradiseco.bubblepop.service.DrinkService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class DrinkRepositoryTests {

    @Mock
    private DrinkRepo drinkRepo;

    @InjectMocks
    private DrinkService drinkService;

    @Test
    public void addDrink_ShouldSaveDrink() {
        // Arrange
        DrinkEntity drink = new DrinkEntity();
        drink.setName("Coffee");

        when(drinkRepo.save(drink)).thenReturn(drink);

        // Act
        DrinkEntity savedDrink = drinkService.addDrink(drink);

        // Assert
        assertEquals(drink, savedDrink);
        verify(drinkRepo, times(1)).save(drink);
    }

    @Test
    public void removeDrink_ShouldDeleteDrink() {
        // Arrange
        Long drinkId = 1L;

        // Act
        drinkService.removeDrink(drinkId);

        // Assert
        verify(drinkRepo, times(1)).deleteById(drinkId);
    }

    @Test
    public void setDrinkPrice_ValidId_ShouldUpdatePrice() {
        // Arrange
        Long drinkId = 1L;
        Integer newPrice = 5;

        DrinkEntity drinkEntity = new DrinkEntity();
        drinkEntity.setId(drinkId);
        drinkEntity.setPrice(10);

        when(drinkRepo.findById(drinkId)).thenReturn(Optional.of(drinkEntity));
        when(drinkRepo.save(drinkEntity)).thenReturn(drinkEntity);

        // Act
        Long updatedId = drinkService.setDrinkPrice(drinkId, newPrice);

        // Assert
        assertEquals(drinkId, updatedId);
        assertEquals(newPrice, drinkEntity.getPrice());
        verify(drinkRepo, times(1)).findById(drinkId);
        verify(drinkRepo, times(1)).save(drinkEntity);
    }

    @Test
    public void setDrinkPrice_InvalidId_ShouldThrowNoSuchElementException() {
        // Arrange
        Long drinkId = 1L;
        Integer newPrice = 5;

        when(drinkRepo.findById(drinkId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> drinkService.setDrinkPrice(drinkId, newPrice));
        verify(drinkRepo, times(1)).findById(drinkId);
        verify(drinkRepo, never()).save(any(DrinkEntity.class));
    }

    @Test
    public void getAllDrinks_ShouldReturnAllDrinks() {
        // Arrange
        List<DrinkEntity> expectedDrinks = new ArrayList<>();
        expectedDrinks.add(new DrinkEntity());
        expectedDrinks.add(new DrinkEntity());

        when(drinkRepo.findAll()).thenReturn(expectedDrinks);

        // Act
        List<DrinkEntity> actualDrinks = drinkService.getAllDrinks();

        // Assert
        assertEquals(expectedDrinks.size(), actualDrinks.size());
        assertTrue(actualDrinks.containsAll(expectedDrinks));
        verify(drinkRepo, times(1)).findAll();
    }
}