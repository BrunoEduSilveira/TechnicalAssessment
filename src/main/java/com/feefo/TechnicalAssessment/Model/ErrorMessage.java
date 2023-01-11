package com.feefo.TechnicalAssessment.Model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorMessage {

    private Date currentDate;
    private String message;
}
