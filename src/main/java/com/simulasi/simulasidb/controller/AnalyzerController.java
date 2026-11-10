package com.simulasi.simulasidb.controller;

import com.simulasi.simulasidb.dto.response.BaseResponse;
import com.simulasi.simulasidb.dto.response.CharAnalysisResponse;
import com.simulasi.simulasidb.dto.request.CharAnalysisRequest;
import com.simulasi.simulasidb.entity.CharAnalysis;
import com.simulasi.simulasidb.service.CharAnalysisService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
public class AnalyzerController {
    private final CharAnalysisService charAnalysisService;

    @GetMapping("/find")
    public BaseResponse<List<CharAnalysisResponse>> cari(
            @RequestParam(required = false) String kalimat) {
        return BaseResponse.ok(charAnalysisService.cari(kalimat));
    }

    @PostMapping("/v1")
    public BaseResponse<CharAnalysisResponse> simpan(
            @Valid
            @RequestBody CharAnalysisRequest request,
            @RequestHeader(value = "X-Request-Id", required = false) String requestId) {
        System.out.println("X-Request-Id: " + requestId + ", kalimat: " + request.getKalimat());
        return BaseResponse.ok(charAnalysisService.simpan(request));
    }

    @GetMapping("/v1/{id}")
    public BaseResponse<CharAnalysisResponse> cariById(@PathVariable Long id) {
        CharAnalysisResponse hasil = charAnalysisService.cariById(id);
        return BaseResponse.ok(charAnalysisService.cariById(id));
    }

    @GetMapping("/v1/minimal")
    public BaseResponse<List<CharAnalysisResponse>> minimal(@RequestParam int min) {
        return BaseResponse.ok(charAnalysisService.minimal(min));
    }

    @GetMapping("/v1/terbaru")
    public BaseResponse<List<CharAnalysisResponse>> terbaru() {
        return BaseResponse.ok(charAnalysisService.terbaru());
    }

    @PostMapping("/from-quote")
    public BaseResponse<CharAnalysisResponse> fromQuote(){
        return BaseResponse.ok(charAnalysisService.analisisDariQuote());
    }

    @PostMapping("/v1/from-quote/{id}")
    public BaseResponse<CharAnalysisResponse> fromQuoteById(@PathVariable Long id) {
        return BaseResponse.ok(charAnalysisService.analisisDariQuote(id));
    }
}
