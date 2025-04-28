package org.apache.commons.text;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParseException;
import java.text.ParsePosition;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CompositeFormatMockingTests {

    Format mockParser = mock(Format.class);
    Format mockFormatter = mock(Format.class);
    CompositeFormat composite = new CompositeFormat(mockParser, mockFormatter);

    @Test
    void testFormatDelegatesToFormatter() {
        StringBuffer buffer = new StringBuffer();
        FieldPosition pos = new FieldPosition(0);

        when(mockFormatter.format(any(), any(StringBuffer.class), any(FieldPosition.class)))
                .thenAnswer(invocation -> {
                    StringBuffer b = invocation.getArgument(1);
                    b.append("formatted");
                    return b;
                });

        StringBuffer result = composite.format("input", buffer, pos);

        assertEquals("formatted", result.toString());
    }

    @Test
    void testParseObjectDelegatesToParser() {
        ParsePosition parsePosition = new ParsePosition(0);
        when(mockParser.parseObject(anyString(), any(ParsePosition.class))).thenReturn("parsedObject");

        Object parsed = composite.parseObject("inputString", parsePosition);

        assertEquals("parsedObject", parsed);
    }

    @Test
    void testReformatParseThrowsException() throws ParseException {
        when(mockParser.parseObject(anyString())).thenThrow(new ParseException("fail", 0));

        assertThrows(ParseException.class, () -> composite.reformat("badinput"));
    }
}
