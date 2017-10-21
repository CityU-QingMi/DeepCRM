    @Test
    public void testSetContentLengthAndWriteInsufficientBytes() throws Exception
    {
        server.setHandler(new SetContentLengthAndWriteInsufficientBytesHandler(false));
        server.start();

        HttpTester.Response response = executeRequest();
        assertThat("response is error", response.getStatus(), is(500));
        assertFalse("response not eof", response.isEarlyEOF());
    }
