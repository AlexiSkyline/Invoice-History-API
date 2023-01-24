package com.alexiskyline.inventory.repository;

import com.alexiskyline.inventory.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends CrudRepository<User, Long> {
    User findByUserName(String username);
}
