    @Test
    public void testConvert01() {
        final ThreadContext.ContextStack stack = new MutableThreadContextStack(
                Arrays.asList("value1", "another2"));

        final String converted = this.converter.convertToDatabaseColumn(stack);

        assertNotNull("The converted value should not be null.", converted);

        final ThreadContext.ContextStack reversed = this.converter
                .convertToEntityAttribute(converted);

        assertNotNull("The reversed value should not be null.", reversed);
        assertEquals("The reversed value is not correct.", stack.asList(),
                reversed.asList());
    }
