    @Test
    public void testHandlerDoesNotSetHandled() throws Exception
    {
        DoesNotSetHandledHandler handler = new DoesNotSetHandledHandler(false);
        server.setHandler(handler);
        server.start();

        HttpTester.Response response = executeRequest();

        assertThat("response code", response.getStatus(), is(404));
        assertThat("no exceptions", handler.failure(), is(nullValue()));
    }
