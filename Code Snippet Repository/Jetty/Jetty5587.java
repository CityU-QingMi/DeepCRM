    @Test
    public void testTokenizer4()
    {
        QuotedStringTokenizer tok = new QuotedStringTokenizer("abc'def,ghi'jkl",",");
        tok.setSingle(false);
        assertEquals("abc'def",tok.nextToken());
        assertEquals("ghi'jkl",tok.nextToken());
        tok = new QuotedStringTokenizer("abc'def,ghi'jkl",",");
        tok.setSingle(true);
        assertEquals("abcdef,ghijkl",tok.nextToken());
    }
