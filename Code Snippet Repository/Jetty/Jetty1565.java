    @Test
    public void testUnQuote()
    {
        assertThat(QuotedCSV.unquote(""),is(""));
        assertThat(QuotedCSV.unquote("\"\""),is(""));
        assertThat(QuotedCSV.unquote("foo"),is("foo"));
        assertThat(QuotedCSV.unquote("\"foo\""),is("foo"));
        assertThat(QuotedCSV.unquote("f\"o\"o"),is("foo"));
        assertThat(QuotedCSV.unquote("\"\\\"foo\""),is("\"foo"));
        assertThat(QuotedCSV.unquote("\\foo"),is("\\foo"));
    }
