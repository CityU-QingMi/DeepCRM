    @Test
    public void testBigContentDelimitedByEOFWithSlowRequestHTTP10() throws Exception
    {
        try
        {
            testContentDelimitedByEOFWithSlowRequest(HttpVersion.HTTP_1_0, 128 * 1024);
        }
        catch(ExecutionException e)
        {
            assertThat(e.getCause(), Matchers.instanceOf(BadMessageException.class));
            assertThat(e.getCause().getMessage(), Matchers.containsString("Unknown content"));
        }
    }
