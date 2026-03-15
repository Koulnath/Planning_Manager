package com.g9;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class SchemaResourceTest {
    @Test
    public void schemaSqlIsOnClasspathAndNotEmpty() throws Exception {
        InputStream in = getClass().getClassLoader().getResourceAsStream("schema.sql");
        assertNotNull("schema.sql should be on classpath", in);
        String content;
        try (Scanner s = new Scanner(in, StandardCharsets.UTF_8.name())) {
            s.useDelimiter("\\A");
            content = s.hasNext() ? s.next() : "";
        }
        assertFalse("schema.sql should not be empty", content.trim().isEmpty());
    }
}
