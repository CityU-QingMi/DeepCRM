    @Test
    public void testSetContentLengthAndWriteExactlyThatAmountOfBytes() throws Exception
    {
        server.setHandler(new SetContentLengthAndWriteThatAmountOfBytesHandler(false));
        server.start();

        HttpTester.Response response = executeRequest();

        assertThat("response code", response.getStatus(), is(200));
        assertThat("response body", response.getContent(), is("foo"));
        assertHeader(response, "content-length", "3");
    }
