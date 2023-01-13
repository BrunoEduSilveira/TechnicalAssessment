package com.feefo.TechnicalAssessment.Service;

import com.feefo.TechnicalAssessment.Model.Job;
import com.feefo.TechnicalAssessment.Repository.NormalizedJobTitleRepository;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Service
@NoArgsConstructor
public class NormalizedJobTitleService {

    @Autowired
    NormalizedJobTitleRepository normalizedJobTitleRepository;

    private static final List<String> normalizedJobTitlesList = List.of("Architect", "Software engineer", "Quantity surveyor","Accountant");
    public static final List<Job> jobList = new ArrayList<>();

    static{
        normalizedJobTitlesList.stream().parallel().forEach(jobTitle -> jobList.add(Job.builder().title(jobTitle).build()));
    }

    public List<Job> AllNormalizedJobsTitles(){

        if(jobList.isEmpty()){
            throw new IllegalStateException("The job title list is empty.");
        }
        return jobList;
    }

    public Job normalizerJobTitle(String titleToCompare){

        if(isNull(titleToCompare) || titleToCompare.isEmpty()){
            throw new IllegalArgumentException("The job title must not be null or empty");
        }

        return matchingTitles(titleToCompare);
    }

    private Job matchingTitles (String titleToCompare){

        double bestSimilarity = Double.MIN_VALUE;
        Job bestMatchJob = Job.builder().build();

        for (Job job : jobList) {
            double actualSimilarity = calculateSimilarity(titleToCompare.toUpperCase(), job.getTitle().toUpperCase());

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
