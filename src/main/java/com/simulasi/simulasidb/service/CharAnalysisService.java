package com.simulasi.simulasidb.service;

import com.simulasi.simulasidb.dto.response.CharAnalysisResponse;
import com.simulasi.simulasidb.dto.request.CharAnalysisRequest;
import com.simulasi.simulasidb.entity.CharAnalysis;

import java.util.List;
import java.util.Optional;

public interface CharAnalysisService {
    List<CharAnalysisResponse> cari(String kata);
    CharAnalysisResponse simpan(CharAnalysisRequest request);
    CharAnalysisResponse cariById(Long id);
    List<CharAnalysisResponse> minimal(int min);
    List<CharAnalysisResponse> terbaru();
    CharAnalysisResponse analisisDariQuote();
    CharAnalysisResponse analisisDariQuote(Long id);
}
