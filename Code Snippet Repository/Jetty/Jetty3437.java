    @Test
    public void testSetContentLengthFlushAndWriteInsufficientBytes() throws Exception
    {
        server.setHandler(new SetContentLengthAndWriteInsufficientBytesHandler(true));
        server.start();
        
        HttpTester.Response response = executeRequest();
        assertThat("response code", response.getStatus(), is(200));
        assertHeader(response, "content-length", "6");
        byte content[] = response.getContentBytes();
        assertThat("content bytes", content.length, is(0));
        assertTrue("response eof", response.isEarlyEOF());
    }
