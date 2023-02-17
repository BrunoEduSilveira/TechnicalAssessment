package com.feefo.TechnicalAssessment.Service;

import com.feefo.TechnicalAssessment.Model.Job;
import com.feefo.TechnicalAssessment.Repository.NormalizedJobTitleRepository;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
@NoArgsConstructor
public class NormalizedJobTitleService {

    @Autowired
    NormalizedJobTitleRepository normalizedJobTitleRepository;

    private static final List<String> normalizedJobTitlesList = List.of("Architect", "Software engineer", "Quantity surveyor", "Accountant");
    public static final List<Job> jobList = new ArrayList<>();

    static {
        normalizedJobTitlesList.stream().parallel().forEach(jobTitle -> jobList.add(Job.builder().title(jobTitle).build()));
    }

    public List<Job> AllNormalizedJobsTitles() {

        if (jobList.isEmpty()) {
            throw new IllegalStateException("The job title list is empty.");
        }
        return jobList;
    }

    public Job normalizerJobTitle(String titleToCompare) {

        if (StringUtils.isBlank(titleToCompare)) {
            throw new ResourceNotFoundException("The job title must not be null or empty");
        }

        return matchingTitles(titleToCompare);
    }

    private Job matchingTitles(String titleToCompare) {
        return jobList.stream()
                .filter(job -> Objects.nonNull(job.getTitle()))
                .max(Comparator.comparingDouble(job ->
                        calculateSimilarity(titleToCompare.toUpperCase(), job.getTitle().toUpperCase())))
                .orElseThrow(() -> new IllegalStateException("The job title list is empty."));
    }

    private double calculateSimilarity(String titleToCompare, String titleNormalized) {

        double maxLength = Double.max(titleToCompare.length(), titleNormalized.length());
        if (maxLength > 0) {
            return (maxLength - StringUtils.getLevenshteinDistance(titleToCompare, titleNormalized)) / maxLength;
        }
        return 1.0;
    }
}
