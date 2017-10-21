    @Test
    public void testHandlerSetsHandledAndWritesSomeContent() throws Exception
    {
        SetHandledWriteSomeDataHandler handler = new SetHandledWriteSomeDataHandler(false);
        server.setHandler(handler);
        server.start();

        HttpTester.Response response = executeRequest();

        assertThat("response code", response.getStatus(), is(200));
        assertHeader(response, "content-length", "6");
        assertThat("no exceptions", handler.failure(), is(nullValue()));
    }
