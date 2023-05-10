package com.paradiseco.bubblepop.repository;

import com.paradiseco.bubblepop.entity.OrderEntity;
import com.paradiseco.bubblepop.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderRepo extends CrudRepository<OrderEntity, Long> {
    public List<OrderEntity> findAllByUserNull();
    public List<OrderEntity> findAllByUser(UserEntity user);
    public List<OrderEntity> findAll();
}
