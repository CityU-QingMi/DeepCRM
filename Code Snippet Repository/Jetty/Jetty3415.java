    @Test
    public void testWriteAndSetContentLength() throws Exception
    {
        WriteAndSetContentLengthHandler handler = new WriteAndSetContentLengthHandler(false);
        server.setHandler(handler);
        server.start();

        HttpTester.Response response = executeRequest();

        assertThat("response code", response.getStatus(), is(200));
        assertThat("no exceptions", handler.failure(), is(nullValue()));
        //TODO: jetty ignores setContentLength and sends transfer-encoding header. Correct?
    }
