package com.inventarysystem.models.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.inventarysystem.models.TypeMove;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "inventary")
public class Move implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sourceStoreId;
    private String targetStoreId;
    private Integer quantity;
    private LocalDateTime movedAt;

    @Enumerated(EnumType.STRING)
    private TypeMove type;
}
