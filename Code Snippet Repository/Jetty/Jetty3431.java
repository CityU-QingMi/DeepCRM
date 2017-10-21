    @Test
    public void testHandledWriteFlushWriteMoreAndThrow() throws Exception
    {
        server.setHandler(new WriteFlushWriteMoreHandler(true));
        server.start();

        HttpTester.Response response = executeRequest();

        // Since the 200 was committed, the 500 did not get the chance to be written
        assertThat("response code", response.getStatus(), is(200));
        if (!"HTTP/1.0".equals(httpVersion))
            assertHeader(response, "transfer-encoding", "chunked");
    }
