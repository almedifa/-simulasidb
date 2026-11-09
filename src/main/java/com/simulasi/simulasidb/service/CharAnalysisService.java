package com.simulasi.simulasidb.service;

import com.simulasi.simulasidb.dto.response.CharAnalysisResponse;
import com.simulasi.simulasidb.entity.CharAnalysis;

import java.util.List;

public interface CharAnalysisService {
    List<CharAnalysisResponse> cari(String kata);
}
