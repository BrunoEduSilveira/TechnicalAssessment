package com.feefo.TechnicalAssessment.Service;

import com.feefo.TechnicalAssessment.Model.NormalizedJob;
import com.feefo.TechnicalAssessment.Repository.NormalizedJobTitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NormalizedJobTitleService {

    @Autowired
    NormalizedJobTitleRepository normalizedJobTitleRepository;

    private static final List<String> jobTitlesList =
            List.of("Architect", "Software engineer", "Quantity surveyor","Accountant");

    private static final List<NormalizedJob> normalizedJobTitleList = new ArrayList<>();

    static {
        jobTitlesList.stream().parallel().forEach(jobTitle -> normalizedJobTitleList.add(NormalizedJob.builder().title(jobTitle).build()));
    }

    public List<NormalizedJob> AllNormalizedJobsTitles(){
        return normalizedJobTitleList;
    }


}
