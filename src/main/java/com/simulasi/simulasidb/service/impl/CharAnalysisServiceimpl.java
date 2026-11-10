package com.simulasi.simulasidb.service.impl;

import com.simulasi.simulasidb.dto.feign.QuoteResponse;
import com.simulasi.simulasidb.dto.response.CharAnalysisResponse;
import com.simulasi.simulasidb.dto.request.CharAnalysisRequest;
import com.simulasi.simulasidb.entity.CharAnalysis;
import com.simulasi.simulasidb.exception.AnalysisAlreadyExistException;
import com.simulasi.simulasidb.exception.AnalysisNotFoundException;
import com.simulasi.simulasidb.exception.QuoteUnavailableException;
import com.simulasi.simulasidb.exception.QuoteNotFoundException;
import com.simulasi.simulasidb.repository.CharAnalysisRepository;
import com.simulasi.simulasidb.rest.QuoteClient;
import com.simulasi.simulasidb.service.CharAnalysisService;
import com.simulasi.simulasidb.util.CharCounter;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
public class CharAnalysisServiceimpl implements CharAnalysisService {
    private final CharAnalysisRepository charAnalysisRepository;
    private final QuoteClient client;

    @Override
    public CharAnalysisResponse simpan(CharAnalysisRequest request) {
        if (charAnalysisRepository.existsByKalimatIgnoreCase(request.getKalimat())) {
            throw new AnalysisAlreadyExistException(request.getKalimat());
        }



        return save(request.getKalimat(), null);
    }


    @Override
    public CharAnalysisResponse cariById(Long id) {
        CharAnalysis data =  charAnalysisRepository.findById(id).orElseThrow(() -> new AnalysisNotFoundException(id));

              return   toResponse(data);
    }

    @Override
    public List<CharAnalysisResponse> minimal(int min) {
        return charAnalysisRepository.findWithMinimumTotalHuruf(min).stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public List<CharAnalysisResponse> terbaru() {
        return charAnalysisRepository.findLatestFive().stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public CharAnalysisResponse analisisDariQuote() {
        QuoteResponse quote;

        try {
            quote = client.getQuote();
        }catch (FeignException feignException){
            throw new QuoteUnavailableException("Service unavailable");
        }
        return save(quote.getQuote(), quote.getAuthor());
    }

    @Override
    public CharAnalysisResponse analisisDariQuote(Long id) {
        QuoteResponse quote;
        try {
            quote = client.getQuoteById(id);
        } catch (FeignException.NotFound exception) {
            throw new QuoteNotFoundException(id);
        } catch (FeignException exception) {
            throw new QuoteUnavailableException("DummyJSON quote service unavailable");
        }
        return save(quote.getQuote(), quote.getAuthor());
    }

    private CharAnalysisResponse save(String kalimat, String author) {
        CharCounter counter = new CharCounter(kalimat);

        CharAnalysis data = new CharAnalysis();
        data.setKalimat(kalimat);
        data.setAuthor(author);
        data.setTotalHuruf(counter.getTotalHuruf());
        data.setCreatedAT(LocalDateTime.now());
        charAnalysisRepository.save(data);

        return CharAnalysisResponse.from(data, counter.toMap());
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
