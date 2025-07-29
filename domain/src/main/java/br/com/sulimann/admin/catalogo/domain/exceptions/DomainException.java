package br.com.sulimann.admin.catalogo.domain.exceptions;

public class DomainException extends RuntimeException{

    public DomainException(String message) {
        super(message, null, true, false);
    }
}
