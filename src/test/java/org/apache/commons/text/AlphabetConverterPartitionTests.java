package org.apache.commons.text;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AlphabetConverterPartitionTests {
    private static AlphabetConverter converter;

    @BeforeAll
    static void initConverter() {
        Character[] original = {'a', 'b', 'c', 'd'};
        Character[] encodings = {'0', '1', '2', 'd'};
        Character[] doNotEncode = {'d'};
        converter = AlphabetConverter.createConverterFromChars(original, encodings, doNotEncode);
    }

    @Test
    void encodeSimpleString() throws UnsupportedEncodingException {
        String input = "abc";
        assertEquals("012", converter.encode(input));
    }

    @Test
    void decodeSimpleString() throws UnsupportedEncodingException {
        String input = "012";
        assertEquals("abc", converter.decode(input));
    }

    @Test
    void encodeAndDecodeEmptyString() throws UnsupportedEncodingException {
        String input = "";
        assertEquals("", converter.encode(input));
        assertEquals("", converter.decode(input));
    }

    @Test
    void handlesSingleChar() throws UnsupportedEncodingException {
        String input = "a";
        String encoded = "0";
        assertEquals("0", converter.encode(input));
        assertEquals("a", converter.decode(encoded));
    }

    @Test
    void doesNotHandleDoNotEncodeChars() throws UnsupportedEncodingException {
        String input = "d";
        String encoded = "d";
        assertEquals("d", converter.encode(input));
        assertEquals("d", converter.decode(encoded));
    }

    @Test
    void throwsOnUnknownChars() {
        assertThrows(UnsupportedEncodingException.class, () -> converter.encode("z"));
    }

    @Test
    void throwsOnUnknownNums() {
        assertThrows(UnsupportedEncodingException.class, () -> converter.decode("3"));
    }

    @Test
    void handlesMixedValidChars() throws UnsupportedEncodingException {
        String input = "abcd";
        String encoded = "012d";
        assertEquals("012d", converter.encode(input));
        assertEquals("abcd", converter.decode(encoded));
    }
}
