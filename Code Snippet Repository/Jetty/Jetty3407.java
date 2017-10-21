    @Test
    public void testBufferOverflowAndThrow() throws Exception
    {
        OverflowHandler handler = new OverflowHandler(true);
        server.setHandler(handler);
        server.start();

        HttpTester.Response response = executeRequest();

        // Buffer size is too small, so the content is written directly producing a 200 response
        assertThat("response code", response.getStatus(), is(200));
        assertResponseBody(response, "foobar");
        if (!"HTTP/1.0".equals(httpVersion))
            assertHeader(response, "transfer-encoding", "chunked");
        assertThat("no exceptions", handler.failure(), is(nullValue()));
    }
