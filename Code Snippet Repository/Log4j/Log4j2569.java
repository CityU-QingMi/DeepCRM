    @Test
    public void testQuoteLongCharSequenceAsString() throws Exception
    {
        final StringBuilder output = new StringBuilder();
        final StringBuilder input = new StringBuilder();
        final StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < 1111; ++i) {
            input.append('"');
            sb2.append("\\\"");
        }
        final String exp = sb2.toString();
        JsonUtils.quoteAsString(input, output);
        assertEquals(2*input.length(), output.length());
        assertEquals(exp, output.toString());

    }
