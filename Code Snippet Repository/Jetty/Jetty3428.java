    @Test
    public void testHandlerDoesNotSetHandled() throws Exception
    {
        server.setHandler(new DoesNotSetHandledHandler(false));
        server.start();
    
        HttpTester.Response response = executeRequest();

        assertThat("response code", response.getStatus(), is(404));
    }
