    @Test
    public void testQuoteCharSequenceAsString() throws Exception
    {
        final StringBuilder output = new StringBuilder();
        final StringBuilder builder = new StringBuilder();
        builder.append("foobar");
        JsonUtils.quoteAsString(builder, output);
        assertEquals("foobar", output.toString());
        builder.setLength(0);
        output.setLength(0);
        builder.append("\"x\"");
        JsonUtils.quoteAsString(builder, output);
        assertEquals("\\\"x\\\"", output.toString());
    }
