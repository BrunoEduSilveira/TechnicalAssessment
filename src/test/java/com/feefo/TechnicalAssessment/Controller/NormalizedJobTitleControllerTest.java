package com.feefo.TechnicalAssessment.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feefo.TechnicalAssessment.Dto.JobDTO;
import com.feefo.TechnicalAssessment.Model.Job;
import com.feefo.TechnicalAssessment.Service.NormalizedJobTitleService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(value = NormalizedJobTitleController.class)
@ContextConfiguration(classes = NormalizedJobTitleController.class)
@Import({ModelMapper.class})
class NormalizedJobTitleControllerTest {

    private static final String MOCK_TITLE = "Java engineer";
    private static final String MOCK_TITLE_NORMALIZED = "Software engineer";

    @Autowired
    MockMvc mvc;

    @MockBean
    private NormalizedJobTitleService normalizedJobTitleService;

    @Test
    public void whenGetAllJobTitlesThenReturnStatusOk() throws Exception {

        mvc.perform(get("/feefo/jobtitle"))
                .andExpect(status().is2xxSuccessful());

        verify(normalizedJobTitleService).AllNormalizedJobsTitles();
    }

    @Test
    public void whenGetNormalizeJobTitleThenReturnStatusOk() throws Exception {
        JobDTO job = JobDTO.builder().title(MOCK_TITLE).build();
        String requestBody = new ObjectMapper().valueToTree(job).toString();

        when(normalizedJobTitleService.normalizerJobTitle(any())).thenReturn(Job.builder().title(MOCK_TITLE_NORMALIZED).build());
        mvc.perform(get("/feefo/jobtitle/normalizer")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());


        verify(normalizedJobTitleService).normalizerJobTitle(any());
    }
}