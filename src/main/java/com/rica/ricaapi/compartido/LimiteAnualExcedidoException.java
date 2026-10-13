package com.rica.ricaapi.compartido;

public class LimiteAnualExcedidoException extends RuntimeException{
    public LimiteAnualExcedidoException(String mensaje) {
        super(mensaje);
    }
}