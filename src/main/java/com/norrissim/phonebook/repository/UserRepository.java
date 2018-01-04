package com.norrissim.phonebook.repository;

import com.norrissim.phonebook.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by User on 31.12.2017.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User AS u WHERE u.username = ?1")
    User findByName(String username);
}
