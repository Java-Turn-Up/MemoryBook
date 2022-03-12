package com.example.memorybook.repository;

import com.example.memorybook.model.entity.relational_Entity.Posted;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostedRepository extends JpaRepository<Posted,Long> {
}
