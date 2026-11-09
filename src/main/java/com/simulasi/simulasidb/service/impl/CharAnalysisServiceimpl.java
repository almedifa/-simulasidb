package com.simulasi.simulasidb.service.impl;

import com.simulasi.simulasidb.dto.response.CharAnalysisResponse;
import com.simulasi.simulasidb.dto.request.CharAnalysisRequest;
import com.simulasi.simulasidb.entity.CharAnalysis;
import com.simulasi.simulasidb.repository.CharAnalysisRepository;
import com.simulasi.simulasidb.service.CharAnalysisService;
import com.simulasi.simulasidb.util.CharCounter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
public class CharAnalysisServiceimpl implements CharAnalysisService {
    private final CharAnalysisRepository charAnalysisRepository;

    @Override
    public CharAnalysisResponse simpan(CharAnalysisRequest request) {
        CharCounter counter = new CharCounter(request.getKalimat());
        CharAnalysis data = new CharAnalysis();
        data.setKalimat(request.getKalimat());
        data.setTotalHuruf(counter.getTotalHuruf());
        data.setCreatedAT(LocalDateTime.now());

        CharAnalysis tersimpan = charAnalysisRepository.save(data);
        return CharAnalysisResponse.from(tersimpan, counter.toMap());
    }

    @Override
    public List<CharAnalysisResponse> cari(String kata) {
        List<CharAnalysis> semua;
        if (kata == null || kata.isBlank()){
            semua = charAnalysisRepository.findAll();
        }
        else{
            semua= charAnalysisRepository.findByKalimatContainingIgnoreCase(kata);

        }
        List<CharAnalysisResponse> hasil = new ArrayList<>();
        for (CharAnalysis data: semua){
            hasil.add(toResponse(data));
        }
        return hasil;
    }

    private CharAnalysisResponse toResponse(CharAnalysis data){
        CharCounter counter = new CharCounter(data.getKalimat());
        return CharAnalysisResponse.from(data, counter.toMap());
    }
}
