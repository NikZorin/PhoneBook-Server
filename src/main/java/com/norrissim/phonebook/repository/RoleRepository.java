package com.norrissim.phonebook.repository;

import com.norrissim.phonebook.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by User on 01.01.2018.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("SELECT r FROM Role AS r where role='USER'")
    Role getUserRole();
}
