    @Test
    public void testConvert02() {
        final StackTraceElement element = new StackTraceElement("org.apache.logging.TestWithPackage",
                "testConvert02", "TestWithPackage.java", -1);

        final String converted = this.converter.convertToDatabaseColumn(element);

        assertNotNull("The converted value should not be null.", converted);
        assertEquals("The converted value is not correct.",
                "org.apache.logging.TestWithPackage.testConvert02(TestWithPackage.java)",
                converted);

        final StackTraceElement reversed = this.converter.convertToEntityAttribute(converted);

        assertNotNull("The reversed value should not be null.", reversed);
        assertEquals("The class name is not correct.", "org.apache.logging.TestWithPackage", reversed.getClassName());
        assertEquals("The method name is not correct.", "testConvert02", reversed.getMethodName());
        assertEquals("The file name is not correct.", "TestWithPackage.java", reversed.getFileName());
        assertEquals("The line number is not correct.", -1, reversed.getLineNumber());
        assertFalse("The native flag should be false.", reversed.isNativeMethod());
    }
