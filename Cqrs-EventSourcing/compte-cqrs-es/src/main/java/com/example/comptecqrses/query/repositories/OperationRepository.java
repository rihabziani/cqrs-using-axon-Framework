package com.example.comptecqrses.query.repositories;

import com.example.comptecqrses.query.entities.Account;
import com.example.comptecqrses.query.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation,Long> {
}
