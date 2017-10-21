    @Test
    public void testHandlerSetsHandledTrueOnlyAndThrow() throws Exception
    {
        OnlySetHandledHandler handler = new OnlySetHandledHandler(true);
        server.setHandler(handler);
        server.start();

        HttpTester.Response response = executeRequest();

        assertThat("response code", response.getStatus(), is(500));
        assertThat("no exceptions", handler.failure(), is(nullValue()));
    }
