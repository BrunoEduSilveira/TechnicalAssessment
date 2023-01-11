package com.feefo.TechnicalAssessment.Repository;

import com.feefo.TechnicalAssessment.Model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NormalizedJobTitleRepository extends JpaRepository<Job, String> {
}

