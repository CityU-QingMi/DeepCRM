    @Test
    public void testHandlerDoesNotSetHandledAndThrow() throws Exception
    {
        server.setHandler(new DoesNotSetHandledHandler(true));
        server.start();
    
        HttpTester.Response response = executeRequest();

        assertThat("response code", response.getStatus(), is(500));
    }
