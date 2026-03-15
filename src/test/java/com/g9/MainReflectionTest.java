package com.g9;

import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class MainReflectionTest {
    @Test
    public void mainMethodExistsWithProperSignature() throws Exception {
        Class<?> cls = Class.forName("com.g9.Main");
        Method m = cls.getMethod("main", String[].class);
        assertNotNull(m);
        assertTrue(Modifier.isStatic(m.getModifiers()));
        assertEquals(void.class, m.getReturnType());
    }
}
