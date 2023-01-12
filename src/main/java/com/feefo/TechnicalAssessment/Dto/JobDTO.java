package com.feefo.TechnicalAssessment.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class JobDTO {
    @NotNull
    private String title;
}
