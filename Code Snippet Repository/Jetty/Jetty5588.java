    private void checkTok(QuotedStringTokenizer tok,boolean delim,boolean quotes)
    {
        assertTrue(tok.hasMoreElements());
        assertTrue(tok.hasMoreTokens());
        assertEquals("abc",tok.nextToken());
        if (delim)assertEquals(",",tok.nextToken());
        if (delim)assertEquals(" ",tok.nextToken());

        assertEquals(quotes?"\"d\\\"'\"":"d\"'",tok.nextElement());
        if (delim)assertEquals(",",tok.nextToken());
        assertEquals(quotes?"'p\\',y'":"p',y",tok.nextToken());
        if (delim)assertEquals(" ",tok.nextToken());
        assertEquals("z",tok.nextToken());
        assertFalse(tok.hasMoreTokens());
    }
