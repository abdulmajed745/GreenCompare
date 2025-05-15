package org.example;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class CSVToMySQLImporterTest {

    @Test
    public void testCleanValue_removesQuotesAndTrims() {
        String input = "  \"Some \\\"quoted\\\" text\"  ";
        String expected = "Some \"quoted\" text";
        String actual = CSVToMySQLImporter.cleanValue(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testCleanValue_handlesDoubleQuotes() {
        String input = "\"This is a \"\"quoted\"\" word\"";
        String expected = "This is a \"quoted\" word";
        String actual = CSVToMySQLImporter.cleanValue(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testCleanValue_emptyString() {
        String input = "";
        String expected = "";
        String actual = CSVToMySQLImporter.cleanValue(input);
        assertEquals(expected, actual);
    }
}
