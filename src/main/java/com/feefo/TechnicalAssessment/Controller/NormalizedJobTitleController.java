package com.feefo.TechnicalAssessment.Controller;

import com.feefo.TechnicalAssessment.Model.NormalizedJob;
import com.feefo.TechnicalAssessment.Service.NormalizedJobTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/test")
public class NormalizedJobTitleController {

    @Autowired
    NormalizedJobTitleService normalizedJobTitleService;

    @GetMapping
    public ResponseEntity<List<NormalizedJob>> getAllJobTitles(){
        return ResponseEntity.status(HttpStatus.OK).body(normalizedJobTitleService.AllNormalizedJobsTitles());
    }
}
