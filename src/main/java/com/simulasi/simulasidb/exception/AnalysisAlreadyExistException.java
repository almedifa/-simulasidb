package com.simulasi.simulasidb.exception;

public class AnalysisAlreadyExistException extends RuntimeException {
    public AnalysisAlreadyExistException(String kalimat) {
        super("Analisis untuk kalimat \"" + kalimat + "\" sudah ada");
    }
}
