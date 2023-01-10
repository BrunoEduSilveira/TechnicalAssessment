package com.feefo.TechnicalAssessment.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NormalizedJob {

    @Id
    private long id;
    private String title;
    private String description;
}
