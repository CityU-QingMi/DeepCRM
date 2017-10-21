    @Test
    public void testHandlerSetsHandledAndWritesSomeContentAndThrow() throws Exception
    {
        server.setHandler(new SetHandledWriteSomeDataHandler(true));
        server.start();

        HttpTester.Response response = executeRequest();

        assertThat("response code", response.getStatus(), is(500));
        assertThat("response body", response.getContent(), not(is("foobar")));
    }
