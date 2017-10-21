    @Test
    public void testTokenizer3()
    {
        QuotedStringTokenizer tok;

        tok = new QuotedStringTokenizer("abc, \"d\\\"'\",'p\\',y' z", " ,",
                                        false,false);
        checkTok(tok,false,false);

        tok = new QuotedStringTokenizer("abc, \"d\\\"'\",'p\\',y' z", " ,",
                                        false,true);
        checkTok(tok,false,true);

        tok = new QuotedStringTokenizer("abc, \"d\\\"'\",'p\\',y' z", " ,",
                                        true,false);
        checkTok(tok,true,false);

        tok = new QuotedStringTokenizer("abc, \"d\\\"'\",'p\\',y' z", " ,",
                                        true,true);
        checkTok(tok,true,true);
    }
