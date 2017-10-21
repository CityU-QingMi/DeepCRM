    @Test
    public void test_StartAsync_Throw_OnError_Dispatch() throws Exception
    {
        test_StartAsync_Throw_OnError(event -> event.getAsyncContext().dispatch("/dispatch"));
        String httpResponse = connector.getResponse("" +
                "GET /ctx/path HTTP/1.1\r\n" +
                "Host: localhost\r\n" +
                "Connection: close\r\n" +
                "\r\n");
        assertThat(httpResponse, containsString("HTTP/1.1 200 "));
    }
