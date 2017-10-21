    @Test
    public void testHeaderWithNumberValues() throws IOException
    {
        String headers[][] =
        {
        { "hello", "1" },
        { "hello", "-1" },
        { "hello", "100" },
        { "hello", "100" },
        { "hello", "100" },
        { "hello", "100" },
        { "hello", "100" },
        { "hello1", "200" } };
        assertHeaders(headers);
    }
