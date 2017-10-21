    @Test
    public void testUnquote()
    {
        assertEquals("abc",QuotedStringTokenizer.unquote("abc"));
        assertEquals("a\"c",QuotedStringTokenizer.unquote("\"a\\\"c\""));
        assertEquals("a'c",QuotedStringTokenizer.unquote("\"a'c\""));
        assertEquals("a\n\r\t",QuotedStringTokenizer.unquote("\"a\\n\\r\\t\""));
        assertEquals("\u0000\u001f ",QuotedStringTokenizer.unquote("\"\u0000\u001f\u0020\""));
        assertEquals("\u0000\u001f ",QuotedStringTokenizer.unquote("\"\u0000\u001f\u0020\""));
        assertEquals("ab\u001ec",QuotedStringTokenizer.unquote("ab\u001ec"));
        assertEquals("ab\u001ec",QuotedStringTokenizer.unquote("\"ab\u001ec\""));
    }
