package org.apache.commons.text;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class StringSubstitutorReplacePartitionTests {

    @Test
    void testReplaceFuncWithSimpleVars() {
        Map<String, String> input = new HashMap<>();
        input.put("cat", "stand");
        StringSubstitutor sub = new StringSubstitutor(input);
        assertEquals("Taco stand", sub.replace("Taco ${cat}"));
    }

    @Test
    void testReplaceFuncWithNoVars() {
        StringSubstitutor sub = new StringSubstitutor();
        assertEquals("Taco cat", sub.replace("Taco cat"));
    }

    @Test
    void testReplaceFuncWithNullVars() {
        StringSubstitutor sub = new StringSubstitutor();
        assertNull(sub.replace((String) null));
    }

    @Test
    void testReplaceFuncWithEmptyVars() {
        StringSubstitutor sub = new StringSubstitutor();
        assertEquals("", sub.replace(""));
    }

    @Test
    void testReplaceFuncWithInvalidVars() {
        Map<String, String> input = new HashMap<>();
        input.put("cat", "stand");
        StringSubstitutor sub = new StringSubstitutor(input);
        assertEquals("Taco ${bell}", sub.replace("Taco ${bell}"));
    }

    @Test
    void testReplaceWithEscapeChar() {
        Map<String, String> input = new HashMap<>();
        input.put("cat", "stand");
        StringSubstitutor sub = new StringSubstitutor(input);
        assertEquals("Taco ${cat}", sub.replace("Taco $${cat}"));
    }

    @Test
    void testReplaceWithDefaultVal() {
        Map<String, String> input = new HashMap<>();
        input.put("cat", "stand");
        StringSubstitutor sub = new StringSubstitutor(input);
        assertEquals("Taco stand goes to taco bell", sub.replace("Taco ${cat} goes to taco ${restaurant:-bell}"));
    }

    @Test
    void testReplaceWithDefaultValFilled() {
        Map<String, String> input = new HashMap<>();
        input.put("cat", "stand");
        input.put("restaurant", "emporium");
        StringSubstitutor sub = new StringSubstitutor(input);
        assertEquals("Taco stand goes to taco emporium", sub.replace("Taco ${cat} goes to taco ${restaurant:-bell}"));
    }
}
