package com.juaracoding.dujavaweb.repo;/*
Created By IntelliJ IDEA 2022.2.3 (Community Edition)
IntelliJ IDEA 2022.3.2 (Community Edition)
Build #IC-223.8617.56, built on January 26, 2023
@Author USER a.k.a. Deby Utari
Java Developer
Created on 27/02/2023 23:31
@Last Modified 27/02/2023 23:31
Version 1.0
*/

import com.juaracoding.dujavaweb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepo extends JpaRepository<User,Long> {

    public List<User> findByEmail(String value);
}

