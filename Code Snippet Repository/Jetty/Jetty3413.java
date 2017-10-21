    @Test
    public void testSetContentLengthAndWriteMoreAndThrow() throws Exception
    {
        SetContentLengthAndWriteMoreBytesHandler handler = new SetContentLengthAndWriteMoreBytesHandler(true);
        server.setHandler(handler);
        server.start();

        HttpTester.Response response = executeRequest();

        // TODO: we throw before response is committed. should we expect 500?
        assertThat("response code", response.getStatus(), is(200));
        assertThat("response body", response.getContent(), is("foo"));
        assertHeader(response, "content-length", "3");
        assertThat("no exceptions", handler.failure(), is(nullValue()));
    }
