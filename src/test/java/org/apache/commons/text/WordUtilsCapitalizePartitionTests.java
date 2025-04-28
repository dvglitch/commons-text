package org.apache.commons.text;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WordUtilsCapitalizePartitionTests {
    @Test
    void testCapitalizeWhitespaceDelimited() {
        String input = "once upon a time";
        String result = WordUtils.capitalize(input);
        assertEquals("Once Upon A Time", result);
    }

    @Test
    void testCapitalizeWithCustomDelimiters() {
        String input = "once uPon.a time";
        String result = WordUtils.capitalize(input, '.');
        assertEquals("Once uPon.A time", result);
    }

    @Test
    void testCapitalizeEmptyString() {
        assertEquals("", WordUtils.capitalize(""));
    }

    @Test
    void testCapitalizeNullInput() {
        assertNull(WordUtils.capitalize(null));
    }

    @Test
    void testCapitalizeWithAllCaps() {
        String input = "ONCE UPON A TIME";
        String result = WordUtils.capitalize(input);
        assertEquals("ONCE UPON A TIME", result);
    }
}
