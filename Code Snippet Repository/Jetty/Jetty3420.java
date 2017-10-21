    @Test
    public void testWriteAndSetContentLengthTooSmallAndThrow() throws Exception
    {
        WriteAndSetContentLengthTooSmallHandler handler = new WriteAndSetContentLengthTooSmallHandler(true);
        server.setHandler(handler);
        server.start();

        HttpTester.Response response = executeRequest();

        // Setting a content-length too small throws an IllegalStateException
        assertThat("response code", response.getStatus(), is(500));
        assertThat("no exceptions", handler.failure(), is(nullValue()));
    }
