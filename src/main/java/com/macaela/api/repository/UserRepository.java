package com.macaela.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.macaela.api.models.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
