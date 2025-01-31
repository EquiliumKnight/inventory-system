package com.inventarysystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventarysystem.models.entity.Inventary;

public interface IInventaryRepository extends JpaRepository<Inventary, Long> {

}
