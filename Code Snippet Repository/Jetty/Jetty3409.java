    @Test
    public void testSetContentLengthAndWriteExactlyThatAmountOfBytes() throws Exception
    {
        SetContentLengthAndWriteThatAmountOfBytesHandler handler = new SetContentLengthAndWriteThatAmountOfBytesHandler(false);
        server.setHandler(handler);
        server.start();

        HttpTester.Response response = executeRequest();

        assertThat("response code", response.getStatus(), is(200));
        assertThat("response body", response.getContent(), is("foo"));
        assertHeader(response, "content-length", "3");
        assertThat("no exceptions", handler.failure(), is(nullValue()));
    }
