package org.apache.commons.text;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class WordUtilsIniitialsPartitionTests {
    @Test
    void testInitialsWhitespaceDelimited() {
        String input = "Jake B Lee";
        String result = WordUtils.initials(input);
        assertEquals("JBL", result);
    }

    @Test
    void testInitialsWithCustomDelimiters() {
        String input = "Jake.B.Lee";
        String result = WordUtils.initials(input, ' ', '.');
        assertEquals("JBL", result);
    }

    @Test
    void testInitialsWithOneWord() {
        String input = "Jake";
        String result = WordUtils.initials(input);
        assertEquals("J", result);
    }

    @Test
    void testInitialsWithMoreThanThreeWords() {
        String input = "Jake B Lee Jr The Third But Without His Shoes";
        String result = WordUtils.initials(input);
        assertEquals("JBLJTTBWHS", result);
    }

    @Test
    void testInitialsEmptyString() {
        assertEquals("", WordUtils.initials(""));
    }

    @Test
    void testInitialsNullInput() {
        assertNull(WordUtils.initials(null));
    }

    @Test
    void testInitialsOnlyDelimiters() {
        String input = "  . . ";
        String result = WordUtils.initials(input, ' ', '.');
        assertEquals("", result);
    }
}
