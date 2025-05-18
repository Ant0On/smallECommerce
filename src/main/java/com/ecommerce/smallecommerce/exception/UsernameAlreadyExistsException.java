package com.ecommerce.smallecommerce.exception;

public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException(String userName) {
        super("Username " + userName + " already exists");
    }
}
