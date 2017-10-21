    @Test
    public void testQuote()
    {
        StringBuffer buf = new StringBuffer();

        buf.setLength(0);
        QuotedStringTokenizer.quote(buf,"abc \n efg");
        assertEquals("\"abc \\n efg\"",buf.toString());

        buf.setLength(0);
        QuotedStringTokenizer.quote(buf,"abcefg");
        assertEquals("\"abcefg\"",buf.toString());

        buf.setLength(0);
        QuotedStringTokenizer.quote(buf,"abcefg\"");
        assertEquals("\"abcefg\\\"\"",buf.toString());

    }
