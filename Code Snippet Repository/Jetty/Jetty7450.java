    @Test
    public void test14_35_BadRange_InvalidSyntax() throws Exception
    {
        // server should ignore all range headers which include
        // at least one syntactically invalid range

        assertBadByteRange("bytes=a-b"); // Invalid due to non-digit entries
        assertBadByteRange("bytes=-"); // Invalid due to missing range ends
        assertBadByteRange("bytes=-1-"); // Invalid due negative to end range
        assertBadByteRange("doublehalfwords=1-2"); // Invalid due to bad key 'doublehalfwords'
    }
