package com.simulasi.simulasidb.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CharAnalysisRequest {

    @NotBlank(message = "kalimat wajib diisi")
    @Size(max = 500, message = "kalimat maksimal 500 karakter" )
    private String kalimat;
}
