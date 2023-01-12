package com.feefo.TechnicalAssessment.Service;

import com.feefo.TechnicalAssessment.Dto.JobDTO;
import com.feefo.TechnicalAssessment.Model.Job;
import com.feefo.TechnicalAssessment.Repository.NormalizedJobTitleRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class NormalizedJobTitleServiceTest {

    private static final String MOCK_TITLE = "Java engineer";

    private static final String MOCK_TITLE_NORMALIZED = "Software engineer";

    private static final String WRONG_MOCK_TITLE_NORMALIZED = "Accountant";

    @InjectMocks
    private NormalizedJobTitleService normalizedJobTitleService;

    @Mock
    private NormalizedJobTitleRepository normalizedJobTitleRepository;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void givenTitleToCompare_WhenNormalizingATitle_ThenShouldReturnANormalizedTitle(){

        //Given
        JobDTO jobTitleToCompare = JobDTO.builder().title(MOCK_TITLE).build();
        JobDTO jobTitleShouldBe = JobDTO.builder().title(MOCK_TITLE_NORMALIZED).build();

        //When
        Job jobNormalized = normalizedJobTitleService.normalizerJobTitle(jobTitleToCompare.getTitle());

        //Then
        Assert.assertEquals(jobNormalized.getTitle(), jobTitleShouldBe.getTitle());
    }

    @Test
    public void givenTitleToCompare_WhenNormalizingATitle_ThenShouldReturnAWrongNormalizedTitle(){

        //Given
        JobDTO jobTitleToCompare = JobDTO.builder().title(MOCK_TITLE).build();
        JobDTO jobTitleMustBe = JobDTO.builder().title(WRONG_MOCK_TITLE_NORMALIZED).build();

        //When
        Job jobNormalized = normalizedJobTitleService.normalizerJobTitle(jobTitleToCompare.getTitle());

        //Then
        Assert.assertNotEquals(jobNormalized.getTitle(), jobTitleMustBe.getTitle());
    }

    @Test
    public void givenEmptyTitleToCompare_WhenNormalizingATitle_ThenShouldReturnAIllegalArgumentException(){
        //given
        JobDTO jobWithEmptyTitle = JobDTO.builder().title("").build();

        //when
        IllegalArgumentException thrown = Assert.assertThrows(
                IllegalArgumentException.class,
                () -> normalizedJobTitleService.normalizerJobTitle(jobWithEmptyTitle.getTitle()));

        //then
        Assert.assertTrue(thrown.getMessage().contentEquals("The job title must not be null or empty"));
    }
}