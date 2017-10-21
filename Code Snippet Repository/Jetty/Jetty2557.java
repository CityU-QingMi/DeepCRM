    @Test
    public void testHeaderWithTextValues() throws IOException
    {
        // different keys
        String headers[][] =
        {
        { "hnum#1", "test1" },
        { "hnum#2", "2test2" },
        { "hnum#3", "test3" } };
        assertHeaders(headers);
    }
