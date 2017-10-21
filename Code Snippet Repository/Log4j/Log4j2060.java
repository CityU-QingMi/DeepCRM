    @Test
    public void testConvert() throws Exception {
        final Object actual = TypeConverters.convert(value, clazz, defaultValue);
        final String assertionMessage = "\nGiven: " + value + "\nDefault: " + defaultValue;
        if (expected != null && expected instanceof char[]) {
            assertArrayEquals(assertionMessage, (char[]) expected, (char[]) actual);
        } else if (expected != null && expected instanceof byte[]) {
            assertArrayEquals(assertionMessage, (byte[]) expected, (byte[]) actual);
        } else {
            assertEquals(assertionMessage, expected, actual);
        }}
