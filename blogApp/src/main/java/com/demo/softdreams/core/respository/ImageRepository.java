package com.demo.softdreams.core.respository;

import com.demo.softdreams.core.entites.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {
}