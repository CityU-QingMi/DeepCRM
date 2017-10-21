    @Test
    public void testConvert02() {
        final ThreadContext.ContextStack stack = new MutableThreadContextStack(
                Arrays.asList("key1", "value2", "my3"));

        final String converted = this.converter.convertToDatabaseColumn(stack);

        assertNotNull("The converted value should not be null.", converted);

        final ThreadContext.ContextStack reversed = this.converter
                .convertToEntityAttribute(converted);

        assertNotNull("The reversed value should not be null.", reversed);
        assertEquals("The reversed value is not correct.", stack.asList(),
                reversed.asList());
    }
