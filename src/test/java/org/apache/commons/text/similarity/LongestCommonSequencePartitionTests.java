package org.apache.commons.text.similarity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openjdk.jmh.annotations.Param;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LongestCommonSequencePartitionTests {
    private final LongestCommonSubsequence sequenceObject = new LongestCommonSubsequence();

    @Test
    void applyThrowsOnNull() {
        assertThrows(IllegalArgumentException.class, () -> sequenceObject.apply(null, null));
        assertThrows(IllegalArgumentException.class, () -> sequenceObject.apply(null, "DOG"));
    }

    @ParameterizedTest
    @CsvSource({
        "'dog', 'dog', 3",
        "'dog', 'do', 2",
        "'dog', 'cat', 0",
        "'abracadabra', 'acada', 5",
        "'abracadabra', 'academy', 4",
        "'DOG', 'dog', 0",
        "'DOG', 'DOG', 3",
        "'this dog', 'is dog', 6",
        "'', 'dog', 0",
        "' ', ' ', 1",
        "'', ' ', 0",
    })
    void lengthTests(String str1, String str2, int res){
        assertEquals(sequenceObject.apply(str1, str2), res);
    }

    @ParameterizedTest
    @CsvSource({
            "'dog', 'dog', 'dog'",
            "'dog', 'do', 'do'",
            "'dog', 'cat', ''",
            "'abracadabra', 'acada', 'acada'",
            "'abracadabra', 'academy', 'acad'",
            "'DOG', 'dog', ''",
            "'DOG', 'DOG', 'DOG'",
            "'this dog', 'is dog', 'is dog'",
            "'', 'dog', ''",
            "' ', ' ', ' '",
            "'', ' ', ''",
    })
    void stringResultTests(String str1, String str2, String res){
        assertEquals(sequenceObject.longestCommonSubsequence(str1, str2), res);
    }
}
