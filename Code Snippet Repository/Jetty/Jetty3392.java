    @Test
    public void testHandlerSetsHandledAndWritesSomeContent() throws Exception
    {
        server.setHandler(new SetHandledWriteSomeDataHandler(false));
        server.start();

        HttpTester.Response response = executeRequest();

        assertThat("response code is 500", response.getStatus(), is(500));
    }
