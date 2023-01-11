package com.feefo.TechnicalAssessment.Service;

import com.feefo.TechnicalAssessment.Model.Job;
import com.feefo.TechnicalAssessment.Repository.NormalizedJobTitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Service
public class NormalizedJobTitleService {

    @Autowired
    NormalizedJobTitleRepository normalizedJobTitleRepository;

    private static final List<String> normalizedJobTitlesList = List.of("Architect", "Software engineer", "Quantity surveyor","Accountant");
    private static final List<Job> jobList = new ArrayList<>();

    static{
        normalizedJobTitlesList.stream().parallel().forEach(jobTitle -> jobList.add(Job.builder().title(jobTitle).build()));
    }

    public ResponseEntity<List<Job>> AllNormalizedJobsTitles(){

        if(jobList.isEmpty()){
            throw new RuntimeException("The job title list is empty.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(jobList);
    }

    public ResponseEntity<Job> normalizerJobTitle(String titleToCompare){

        if(isNull(titleToCompare) || titleToCompare.isEmpty()){
            throw new IllegalArgumentException("The job title must not be null or empty");
        }
        Job bestMatchJob = matchingTitles(titleToCompare);

        return ResponseEntity.status(HttpStatus.OK).body(bestMatchJob);
    }

    private Job matchingTitles (String titleToCompare){

        double bestSimilarity = Double.MIN_VALUE;
        Job bestMatchJob = Job.builder().build();

        for (Job job : jobList) {
            double actualSimilarity = calculateSimilarity(titleToCompare, job.getTitle());

            if(bestSimilarity < actualSimilarity) {
                bestSimilarity = actualSimilarity;
                bestMatchJob = job;
            }
        }
        return bestMatchJob;
    }

    private double calculateSimilarity (String titleToCompare, String titleNormalized){

            double maxLength = Double.max(titleToCompare.length(), titleNormalized.length());
            if(maxLength > 0){
                return (maxLength - StringUtils.getLevenshteinDistance(titleToCompare, titleNormalized)) / maxLength;
            }
        return 1.0;
    }
}
