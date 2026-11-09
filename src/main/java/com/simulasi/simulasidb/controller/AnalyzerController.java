package com.simulasi.simulasidb.controller;

import com.simulasi.simulasidb.dto.response.BaseResponse;
import com.simulasi.simulasidb.dto.response.CharAnalysisResponse;
import com.simulasi.simulasidb.entity.CharAnalysis;
import com.simulasi.simulasidb.service.CharAnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}
