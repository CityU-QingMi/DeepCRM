    @Test
    public void testConvert04() {
        final StackTraceElement element = new StackTraceElement("org.apache.logging.TestIsNativeMethod",
                "testConvert04", null, -2);

        final String converted = this.converter.convertToDatabaseColumn(element);

        assertNotNull("The converted value should not be null.", converted);
        assertEquals("The converted value is not correct.",
                "org.apache.logging.TestIsNativeMethod.testConvert04(Native Method)",
                converted);

        final StackTraceElement reversed = this.converter.convertToEntityAttribute(converted);

        assertNotNull("The reversed value should not be null.", reversed);
        assertEquals("The class name is not correct.", "org.apache.logging.TestIsNativeMethod",
                reversed.getClassName());
        assertEquals("The method name is not correct.", "testConvert04", reversed.getMethodName());
        assertEquals("The file name is not correct.", null, reversed.getFileName());
        assertEquals("The line number is not correct.", -2, reversed.getLineNumber());
        assertTrue("The native flag should be true.", reversed.isNativeMethod());
    }
