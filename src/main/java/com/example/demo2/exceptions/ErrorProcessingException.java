package com.example.demo2.exceptions;

public class ErrorProcessingException extends RuntimeException {

    public ErrorProcessingException() {
        super("Se produjo un error inesperado al intentar procesar su solicitud");
    }

    public ErrorProcessingException(String mensaje) {
        super(mensaje);
    }

    public ErrorProcessingException(String mensaje, Throwable root) {
        super(mensaje, root);
    }
}