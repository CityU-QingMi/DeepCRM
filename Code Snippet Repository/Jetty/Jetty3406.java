    @Test
    public void testBufferOverflow() throws Exception
    {
        OverflowHandler handler = new OverflowHandler(false);
        server.setHandler(handler);
        server.start();

        HttpTester.Response response = executeRequest();

        assertThat("response code", response.getStatus(), is(200));
        assertResponseBody(response, "foobar");
        if (!"HTTP/1.0".equals(httpVersion))
            assertHeader(response, "transfer-encoding", "chunked");
        assertThat("no exceptions", handler.failure(), is(nullValue()));
    }
