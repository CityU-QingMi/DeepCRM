    @Test
    public void testConvert03() {
        final StackTraceElement element = new StackTraceElement("org.apache.logging.TestNoSource",
                "testConvert03", null, -1);

        final String converted = this.converter.convertToDatabaseColumn(element);

        assertNotNull("The converted value should not be null.", converted);
        assertEquals("The converted value is not correct.",
                "org.apache.logging.TestNoSource.testConvert03(Unknown Source)",
                converted);

        final StackTraceElement reversed = this.converter.convertToEntityAttribute(converted);

        assertNotNull("The reversed value should not be null.", reversed);
        assertEquals("The class name is not correct.", "org.apache.logging.TestNoSource", reversed.getClassName());
        assertEquals("The method name is not correct.", "testConvert03", reversed.getMethodName());
        assertEquals("The file name is not correct.", null, reversed.getFileName());
        assertEquals("The line number is not correct.", -1, reversed.getLineNumber());
        assertFalse("The native flag should be false.", reversed.isNativeMethod());
    }
