package com.paradiseco.bubblepop.repository;

import com.paradiseco.bubblepop.entity.DrinkEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DrinkRepo extends CrudRepository<DrinkEntity, Long> {
    List<DrinkEntity> findAll();
}
