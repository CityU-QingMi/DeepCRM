    @Test
    public void testCharSequenceWithCtrlChars() throws Exception
    {
        final char[] input = new char[] { 0, 1, 2, 3, 4 };
        final StringBuilder builder = new StringBuilder();
        builder.append(input);
        final StringBuilder output = new StringBuilder();
        JsonUtils.quoteAsString(builder, output);
        assertEquals("\\u0000\\u0001\\u0002\\u0003\\u0004", output.toString());
    }
