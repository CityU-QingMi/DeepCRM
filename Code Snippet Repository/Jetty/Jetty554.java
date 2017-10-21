    @Test
    public void testSmallContentDelimitedByEOFWithSlowRequestHTTP10() throws Exception
    {
        try
        {
            testContentDelimitedByEOFWithSlowRequest(HttpVersion.HTTP_1_0, 1024);
        }
        catch(ExecutionException e)
        {
            assertThat(e.getCause(), Matchers.instanceOf(BadMessageException.class));
            assertThat(e.getCause().getMessage(), Matchers.containsString("Unknown content"));
        }
    }
