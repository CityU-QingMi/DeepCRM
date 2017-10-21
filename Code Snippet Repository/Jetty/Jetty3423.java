    @Test
    public void testHandlerSetsHandledTrueOnly() throws Exception
    {
        OnlySetHandledHandler handler = new OnlySetHandledHandler(false);
        server.setHandler(handler);
        server.start();

        HttpTester.Response response = executeRequest();
    
        assertThat("response code", response.getStatus(), is(200));
        if (HttpVersion.HTTP_1_1.asString().equals(httpVersion))
            assertHeader(response, "content-length", "0");
        assertThat("no exceptions", handler.failure(), is(nullValue()));
    }
