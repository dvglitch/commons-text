package org.apache.commons.text.numbers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParsedDecimalBoundaryTests {
    @Test
    void zeroParsesCorrectly() {
        ParsedDecimal pd = ParsedDecimal.from(0.0);
        assertFalse(pd.negative);
        assertEquals(0, pd.digits[0]);
        assertEquals(0, pd.getExponent());
    }

    @Test
    void negativeZeroParsesCorrectly() {
        ParsedDecimal pd = ParsedDecimal.from(-0.0);
        assertFalse(pd.negative);
        assertEquals(0, pd.digits[0]);
        assertEquals(0, pd.getExponent());
    }

    @Test
    void basicDecimalParsesCorrectly() {
        ParsedDecimal pd = ParsedDecimal.from(1.7);
        assertFalse(pd.negative);
        assertArrayEquals(new int[]{1, 7}, pd.digits);
        assertEquals(-1, pd.getExponent());
    }

    @Test
    void negativeDecimalParsesCorrectly() {
        ParsedDecimal pd = ParsedDecimal.from(-2.104);
        assertTrue(pd.negative);
        assertArrayEquals(new int[]{2,1,0,4}, pd.digits);
        assertEquals(-3, pd.getExponent());
    }

    @Test
    void largeDecimalParsesCorrectly() {
        ParsedDecimal pd = ParsedDecimal.from(1.7976931348623157E306);
        assertFalse(pd.negative);
        assertArrayEquals(new int[]{1,7,9,7,6,9,3,1,3,4,8,6,2,3,1,5,6,0,0,0,0}, pd.digits);
        assertEquals(290, pd.getExponent());
    }

    @Test
    void maxDecimalParsesCorrectly() {
        ParsedDecimal pd = ParsedDecimal.from(Double.MAX_VALUE);
        assertFalse(pd.negative);
        assertArrayEquals(new int[]{1,7,9,7,6,9,3,1,3,4,8,6,2,3,1,5,7,0,0,0,0}, pd.digits);
        assertEquals(292, pd.getExponent());
    }

    @Test
    void maxNegativeDecimalParsesCorrectly() {
        ParsedDecimal pd = ParsedDecimal.from(-Double.MAX_VALUE);
        assertTrue(pd.negative);
        assertArrayEquals(new int[]{1,7,9,7,6,9,3,1,3,4,8,6,2,3,1,5,7,0,0,0,0}, pd.digits);
        assertEquals(292, pd.getExponent());
    }

    @Test
    void minDecimalParsesCorrectly() {
        ParsedDecimal pd = ParsedDecimal.from(Double.MIN_VALUE);
        assertFalse(pd.negative);
        assertArrayEquals(new int[]{4,9,0,0,0,0,0}, pd.digits);
        assertEquals(-325, pd.getExponent());
    }

    @Test
    void minNegativeDecimalParsesCorrectly() {
        ParsedDecimal pd = ParsedDecimal.from(-Double.MIN_VALUE);
        assertTrue(pd.negative);
        assertArrayEquals(new int[]{4,9,0,0,0,0,0}, pd.digits);
        assertEquals(-325, pd.getExponent());
    }

    @Test
    void maxDecimalTimes10Throws() {
        assertThrows(IllegalArgumentException.class, () -> ParsedDecimal.from(Double.MAX_VALUE*10));
    }

    @Test
    void minDecimalDividedby10IsParsedAs0() {
        ParsedDecimal pd = ParsedDecimal.from(Double.MIN_VALUE/10);
        assertFalse(pd.negative);
        assertArrayEquals(new int[]{0}, pd.digits);
        assertEquals(0, pd.getExponent());    }
}
