package com.lautypetelin.LoginWithSpringBoot.repository;

import com.lautypetelin.LoginWithSpringBoot.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

}