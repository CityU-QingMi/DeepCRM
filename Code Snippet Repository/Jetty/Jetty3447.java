    @Test
    public void testWriteAndSetContentLengthTooSmall() throws Exception
    {
        server.setHandler(new WriteAndSetContentLengthTooSmallHandler(false));
        server.start();

        HttpTester.Response response = executeRequest();

        // Setting a content-length too small throws an IllegalStateException
        assertThat("response code", response.getStatus(), is(500));
        assertThat("response body", response.getContent(), not(is("foo")));
    }
