    @Test
    public void testTokenizer2()
    {
        QuotedStringTokenizer tok =
            new QuotedStringTokenizer("abc, \"d\\\"'\",'p\\',y' z", " ,",
            false);
        checkTok(tok,false,false);

        tok = new QuotedStringTokenizer("abc, \"d\\\"'\",'p\\',y' z", " ,",
                                        true);
        checkTok(tok,true,false);
    }
