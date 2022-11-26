package maciej.grochowski.fightmetric.ufcstats.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ReadingServiceImplTest {

    @InjectMocks
    private ReadingServiceImpl readingService;

    String nameLine = "<p class=\"b-fight-details__table-text\">Neil Magny</p> ";
    String reachLine = "<p class=\"b-fight-details__table-text\">80\"</p> ";
    String heightLine = "<p class=\"b-fight-details__table-text\">6' 3\"</p>";

    @Test
    void getDataFromParagraph_should_read_proper_name() {
        // given + when
        String nameFromParagraph = readingService.getDataFromParagraph(nameLine);

        // then
        assertEquals("Neil Magny", nameFromParagraph);
    }

    @Test
    void convertToCm_reach_should_multiply_inches_by__2_54() {
        // given
        String reach = readingService.getDataFromParagraph(reachLine);
        BigDecimal actualReach = BigDecimal.valueOf(2.54).multiply(BigDecimal.valueOf(80));

        // when
        BigDecimal calculatedReach = readingService.convertToCm(reach, true);

        // then
        assertEquals(calculatedReach, actualReach);
    }

    @Test
    void convertToCm_height_should_multiply_feet_by__30_48__inches_by__2_54() {
        // given
        String height = readingService.getDataFromParagraph(heightLine);
        BigDecimal actualHeight = BigDecimal.valueOf(30.48).multiply(BigDecimal.valueOf(6))
                .add(BigDecimal.valueOf(2.54).multiply(BigDecimal.valueOf(3)));

        // when
        BigDecimal calculatedReach = readingService.convertToCm(height, false);

        // then
        assertEquals(calculatedReach, actualHeight);
    }
}