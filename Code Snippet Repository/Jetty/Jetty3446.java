    @Test
    public void testWriteAndSetContentLengthAndThrow() throws Exception
    {
        server.setHandler(new WriteAndSetContentLengthHandler(true));
        server.start();

        HttpTester.Response response = executeRequest();

        // Writing the bytes and then setting the content-length commits the response
        assertThat("response code", response.getStatus(), is(200));
        assertThat("response body", response.getContent(), is("foo"));
    }
