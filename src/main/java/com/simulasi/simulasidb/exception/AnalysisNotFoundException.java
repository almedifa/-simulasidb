package com.simulasi.simulasidb.exception;

public class AnalysisNotFoundException extends RuntimeException {
    public AnalysisNotFoundException(Long id) {
        super("Data dengan id" + id + "tidak ditemukan");
    }
}
