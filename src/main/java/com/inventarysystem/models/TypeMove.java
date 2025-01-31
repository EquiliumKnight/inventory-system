package com.inventarysystem.models;

public enum TypeMove {
    IN("IN"), 
    OUT("OUT"), 
    TRANSFER("TRANSFER");

    private final String typeMove;

    private TypeMove(String typeMove) {
        this.typeMove = typeMove;
    }

    @Override
    public String toString() {
        return this.typeMove;
    }
}
