    @Test
    public void testHandledOverflow() throws Exception
    {
        server.setHandler(new OverflowHandler(false));
        server.start();

        HttpTester.Response response = executeRequest();

        assertThat("response code", response.getStatus(), is(200));
        assertResponseBody(response, "foobar");
        if (!"HTTP/1.0".equals(httpVersion))
            assertHeader(response, "transfer-encoding", "chunked");
    }
