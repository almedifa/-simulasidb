package com.simulasi.simulasidb.exception;

public class QuoteNotFoundException extends RuntimeException {
    public QuoteNotFoundException(Long id) {
        super("Quote with id " + id + " not found");
    }
}
