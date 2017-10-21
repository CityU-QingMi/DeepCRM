    @Test
    public void testWriteAndSetContentLengthAndThrow() throws Exception
    {
        WriteAndSetContentLengthHandler handler = new WriteAndSetContentLengthHandler(true);
        server.setHandler(handler);
        server.start();

        HttpTester.Response response = executeRequest();

        assertThat("response code", response.getStatus(), is(200));
        assertThat("no exceptions", handler.failure(), is(nullValue()));
    }
