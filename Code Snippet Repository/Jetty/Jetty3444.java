    @Test
    public void testSetContentLengthAndWriteMoreAndThrow() throws Exception
    {
        server.setHandler(new SetContentLengthAndWriteMoreBytesHandler(true));
        server.start();

        HttpTester.Response response = executeRequest();

        // Setting the content-length and then writing the bytes commits the response
        assertThat("response code", response.getStatus(), is(200));
        assertThat("response body", response.getContent(), is("foo"));
    }
