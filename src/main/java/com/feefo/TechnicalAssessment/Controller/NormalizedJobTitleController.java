package com.feefo.TechnicalAssessment.Controller;

import com.feefo.TechnicalAssessment.Dto.JobDTO;
import com.feefo.TechnicalAssessment.Model.Job;
import com.feefo.TechnicalAssessment.Service.NormalizedJobTitleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/feefo/jobtitle")
public class NormalizedJobTitleController {

    @Autowired
    NormalizedJobTitleService normalizedJobTitleService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<JobDTO>> getAllJobTitles() {
        List<Job> jobList = normalizedJobTitleService.AllNormalizedJobsTitles();
        return ResponseEntity.ok().body(jobList.stream()
                .map(job -> modelMapper.map(job, JobDTO.class))
                .collect(Collectors.toList()));
    }

    @GetMapping("/normalizer")
    public ResponseEntity<JobDTO> normalizeJobTitle(@RequestBody JobDTO jobDTO) {
        Job jobResponse = normalizedJobTitleService.normalizerJobTitle(jobDTO.getTitle());
        return ResponseEntity.ok().body(modelMapper.map(jobResponse, JobDTO.class));
    }
}
