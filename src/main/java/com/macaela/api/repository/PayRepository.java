package com.macaela.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.macaela.api.models.pay.Pay;

@Repository
public interface PayRepository extends JpaRepository<Pay, Long> {

}
