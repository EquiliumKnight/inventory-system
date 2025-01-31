package com.inventarysystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventarysystem.models.entity.Move;

public interface IMoveRepository extends JpaRepository<Move, Long> {

}
