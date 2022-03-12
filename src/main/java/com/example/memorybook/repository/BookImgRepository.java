package com.example.memorybook.repository;

import com.example.memorybook.model.entity.BookImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookImgRepository extends JpaRepository<BookImage,Long> {
}
