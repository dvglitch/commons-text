package org.apache.commons.text;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RandomStringGeneratorPartitionTests {
    @Test
    void testZeroLength() {
        RandomStringGenerator generator = RandomStringGenerator.builder().build();
        assertEquals("", generator.generate(0));
    }

    @Test
    void testNegativeLengthThrows() {
        RandomStringGenerator generator = RandomStringGenerator.builder().build();
        assertThrows(IllegalArgumentException.class, () -> generator.generate(-1));
    }

    @Test
    void testPositiveLength() {
        RandomStringGenerator generator = RandomStringGenerator.builder().withinRange('a', 'z').build();
        String result = generator.generate(10);
        assertEquals(10, result.length());
        assertTrue(result.chars().allMatch(c -> c >= 'a' && c <= 'z'));
    }

    @Test
    void testGenerateWithCharacterList() {
        RandomStringGenerator generator = RandomStringGenerator.builder().selectFrom('X', 'Y', 'Z').build();
        String result = generator.generate(20);
        assertTrue(result.chars().allMatch(c -> c == 'X' || c == 'Y' || c == 'Z'));
    }

    @Test
    void testGenerateFilteredByPredicate() {
        CharacterPredicate vowelPredicate = c -> "aeiouAEIOU".indexOf(c) != -1;
        RandomStringGenerator generator = RandomStringGenerator.builder().withinRange('a', 'z').filteredBy(vowelPredicate).build();
        String result = generator.generate(30);
        assertTrue(result.chars().allMatch(c -> "aeiouAEIOU".indexOf(c) != -1));
    }

    @Test
    void testWithinRangeSingleCharacter() {
        RandomStringGenerator generator = RandomStringGenerator.builder().withinRange('M', 'M').build();
        String result = generator.generate(5);
        assertEquals("MMMMM", result);
    }
}
