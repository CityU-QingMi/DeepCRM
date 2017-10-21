    @Test
    public void testHandledAndFlushWithoutContent() throws Exception
    {
        SetHandledAndFlushWithoutContentHandler handler = new SetHandledAndFlushWithoutContentHandler(false);
        server.setHandler(handler);
        server.start();

        HttpTester.Response response = executeRequest();

        assertThat("response code", response.getStatus(), is(200));
        assertThat("no exceptions", handler.failure(), is(nullValue()));
        if (!"HTTP/1.0".equals(httpVersion))
            assertHeader(response, "transfer-encoding", "chunked");
    }
