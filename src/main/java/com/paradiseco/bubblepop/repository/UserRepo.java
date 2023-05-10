package com.paradiseco.bubblepop.repository;

import com.paradiseco.bubblepop.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends CrudRepository<UserEntity, Long> {
    List<UserEntity> findAll();
    boolean existsByUsername(String username);
    UserEntity findByUsername(String username);
}
