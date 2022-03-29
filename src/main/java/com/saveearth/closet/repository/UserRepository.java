package com.saveearth.closet.repository;

import com.saveearth.closet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //Optional<User> findById(Long id);

}
