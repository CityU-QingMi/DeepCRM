    @Test
    public void testSetContentLengthAndWriteExactlyThatAmountOfBytesAndThrow() throws Exception
    {
        SetContentLengthAndWriteThatAmountOfBytesHandler handler = new SetContentLengthAndWriteThatAmountOfBytesHandler(true);
        server.setHandler(handler);
        server.start();

        HttpTester.Response response = executeRequest();

        //TODO: should we expect 500 here?
        assertThat("response code", response.getStatus(), is(200));
        assertThat("response body", response.getContent(), is("foo"));
        assertHeader(response, "content-length", "3");
        assertThat("no exceptions", handler.failure(), is(nullValue()));
    }
