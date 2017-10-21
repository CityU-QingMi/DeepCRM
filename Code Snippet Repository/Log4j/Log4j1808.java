    @Test
    public void testConvert01() {
        final RuntimeException exception = new RuntimeException("My message 01.");

        final String stackTrace = getStackTrace(exception);

        final String converted = this.converter.convertToDatabaseColumn(exception);

        assertNotNull("The converted value is not correct.", converted);
        assertEquals("The converted value is not correct.", stackTrace, converted);

        final Throwable reversed = this.converter.convertToEntityAttribute(converted);

        assertNotNull("The reversed value should not be null.", reversed);
        assertEquals("The reversed value is not correct.", stackTrace, getStackTrace(reversed));
    }
