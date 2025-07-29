package br.com.sulimann.admin.catalogo.domain.validation;

import br.com.sulimann.admin.catalogo.domain.exceptions.DomainException;

import java.util.Objects;

public abstract class AssertUtils {

    public static void notNull(Object obj, String message){
        if(Objects.isNull(obj)){
            throw new DomainException(message);
        }
    }

    public static void isTrue(boolean validation, String message){
        if(!validation){
            throw new DomainException(message);
        }
    }
}
