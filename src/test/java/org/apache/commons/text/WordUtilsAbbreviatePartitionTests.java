package org.apache.commons.text;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class WordUtilsAbbreviatePartitionTests {

    @ParameterizedTest
    @CsvSource({
        "'Once upon a time there was a witch', 'Once...', 0, 40, '...'",
        "'Once upon a time there was a witch', 'Once upon a...', 10, 40, '...'",
        "'The end', 'The...', 0, 40, '...'",
        "'Once upon a time there was a witch', 'Once upon a...', 10, -1, '...'",
        "'Finale', 'Finale', 0, 10, '...'",
        "'', '', 10, -1, '...'",

    })
    void abbreviateTestsWithAnOutputs(String input, String res, int lowerLimit, int upperLimit, String appendStr) {
        assertEquals(res, WordUtils.abbreviate(input, lowerLimit, upperLimit, appendStr));
    }

    @Test
    void abbreviateTestWithNullReturns() {
        assertNull(WordUtils.abbreviate(null, 0, 10, "..."));
    }

    @Test
    void imbalancedLimitsThrows() {
        assertThrows(IllegalArgumentException.class, () -> WordUtils.abbreviate("input", 10, 5, "..."));
    }

    @Test
    void tooManyNegativeThrows() {
        assertThrows(IllegalArgumentException.class, () -> WordUtils.abbreviate("input", 5, -2, "..."));
    }
}
