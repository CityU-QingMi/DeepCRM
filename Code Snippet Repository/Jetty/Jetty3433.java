    @Test
    public void testHandledOverflow2() throws Exception
    {
        server.setHandler(new Overflow2Handler(false));
        server.start();

        HttpTester.Response response = executeRequest();

        assertThat("response code", response.getStatus(), is(200));
        assertResponseBody(response, "foobarfoobar");
        if (!"HTTP/1.0".equals(httpVersion))
            assertHeader(response, "transfer-encoding", "chunked");
    }
