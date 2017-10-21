    @Test
    public void testHandlerExplicitFlushAndThrow() throws Exception
    {
        server.setHandler(new ExplicitFlushHandler(true));
        server.start();

        HttpTester.Response response = executeRequest();

        // Since the 200 was committed, the 500 did not get the chance to be written
        assertThat("response code", response.getStatus(), is(200));
        assertThat("response body", response.getContent(), is("foobar"));
        if (!"HTTP/1.0".equals(httpVersion))
            assertHeader(response, "transfer-encoding", "chunked");
    }
