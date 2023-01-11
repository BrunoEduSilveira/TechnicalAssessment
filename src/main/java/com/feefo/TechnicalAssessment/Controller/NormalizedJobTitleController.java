package com.feefo.TechnicalAssessment.Controller;

import com.feefo.TechnicalAssessment.Model.Job;
import com.feefo.TechnicalAssessment.Service.NormalizedJobTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/feefo/jobtitle")
public class NormalizedJobTitleController {

    @Autowired
    NormalizedJobTitleService normalizedJobTitleService;

    @GetMapping
    public ResponseEntity<List<Job>> getAllJobTitles(){
        return normalizedJobTitleService.AllNormalizedJobsTitles();
    }

    @GetMapping("/normalizer")
    public ResponseEntity<Job> normalizeJobTitle(@RequestBody Job job){
        return normalizedJobTitleService.normalizerJobTitle(job.getTitle());
    }
}
