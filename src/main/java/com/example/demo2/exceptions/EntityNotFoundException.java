package com.example.demo2.exceptions;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException() {
        super("El registro solicitado no existe");
    }

    public EntityNotFoundException(String mensaje) {
        super(mensaje);
    }

}