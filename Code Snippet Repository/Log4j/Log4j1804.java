    @Test
    public void testConvert01() {
        final StackTraceElement element = new StackTraceElement("TestNoPackage", "testConvert01", "TestNoPackage.java", 1234);

        final String converted = this.converter.convertToDatabaseColumn(element);

        assertNotNull("The converted value should not be null.", converted);
        assertEquals("The converted value is not correct.", "TestNoPackage.testConvert01(TestNoPackage.java:1234)",
                converted);

        final StackTraceElement reversed = this.converter.convertToEntityAttribute(converted);

        assertNotNull("The reversed value should not be null.", reversed);
        assertEquals("The class name is not correct.", "TestNoPackage", reversed.getClassName());
        assertEquals("The method name is not correct.", "testConvert01", reversed.getMethodName());
        assertEquals("The file name is not correct.", "TestNoPackage.java", reversed.getFileName());
        assertEquals("The line number is not correct.", 1234, reversed.getLineNumber());
        assertFalse("The native flag should be false.", reversed.isNativeMethod());
    }
