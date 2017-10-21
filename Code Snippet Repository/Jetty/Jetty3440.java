    @Test
    public void testSetContentLengthAndFlushWriteInsufficientBytes() throws Exception
    {
        server.setHandler(new SetContentLengthAndWriteInsufficientBytesHandler(true));
        server.start();

        HttpTester.Response response = executeRequest();
        assertThat("response has no status", response.getStatus(), is(200));
        assertTrue("response eof", response.isEarlyEOF());
    }
