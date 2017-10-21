    @Test
    public void testSetContentLengthAndWriteMoreBytes() throws Exception
    {
        SetContentLengthAndWriteMoreBytesHandler handler = new SetContentLengthAndWriteMoreBytesHandler(false);
        server.setHandler(handler);
        server.start();

        HttpTester.Response response = executeRequest();

        assertThat("response code", response.getStatus(), is(200));
        // jetty truncates the body when content-length is reached.! This is correct and desired behaviour?
        assertThat("response body", response.getContent(), is("foo"));
        assertHeader(response, "content-length", "3");
        assertThat("no exceptions", handler.failure(), is(nullValue()));
    }
