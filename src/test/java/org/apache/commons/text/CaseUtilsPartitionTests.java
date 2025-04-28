package org.apache.commons.text;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CaseUtilsPartitionTests {

    @Test
    void testNullInput() {
        assertNull(CaseUtils.toCamelCase(null, false));
    }

    @Test
    void testEmptyString() {
        assertEquals("", CaseUtils.toCamelCase("", false));
    }

    @Test
    void testOnlyDelimiters() {
        assertEquals("", CaseUtils.toCamelCase("   ", false));
        assertEquals("", CaseUtils.toCamelCase("@@@", false, '@'));
    }

    @Test
    void testNoDelimiters() {
        assertEquals("teststring", CaseUtils.toCamelCase("teststring", false));
        assertEquals("Teststring", CaseUtils.toCamelCase("teststring", true));
    }

    @Test
    void testDelimitersAtStartMiddleEnd() {
        assertEquals("ohHiThere", CaseUtils.toCamelCase(" oh hi there ", false));
        assertEquals("OhHiThere", CaseUtils.toCamelCase(" oh hi there ", true));
        assertEquals("ohHiThere", CaseUtils.toCamelCase("-oh-hi-there-", false, '-'));
    }

    @Test
    void testCapitalizeFirstLetterTrue() {
        assertEquals("TacoCat", CaseUtils.toCamelCase("taco cat", true));
    }

    @Test
    void testCapitalizeFirstLetterFalse() {
        assertEquals("tacoCat", CaseUtils.toCamelCase("taco cat", false));
    }

    @Test
    void tesTNullDelimiters() {
        assertEquals("tacoCat", CaseUtils.toCamelCase("taco cat", false, null));
    }

    @Test
    void testEmptyDelimiterArray() {
        assertEquals("tacocat", CaseUtils.toCamelCase("tacocat", false, new char[0]));
    }

    @Test
    void testCustomDelimiters() {
        assertEquals("ohHiThere", CaseUtils.toCamelCase("oh@hi@there", false, '@'));
        assertEquals("ohHiThere", CaseUtils.toCamelCase("oh.hi.there", false, '.'));
        assertEquals("ohHiThere", CaseUtils.toCamelCase("oh-hi-there", false, '-'));
    }

    @Test
    void testMixedDelimiters() {
        assertEquals("ohHiThere", CaseUtils.toCamelCase("oh-hi.there", false, '-', '.'));
    }

}
