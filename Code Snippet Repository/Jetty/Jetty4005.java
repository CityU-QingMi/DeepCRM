    @Test
    public void test_StartAsync_OnTimeout_Dispatch() throws Exception
    {
        test_StartAsync_OnTimeout(500, event -> event.getAsyncContext().dispatch("/dispatch"));
        String httpResponse = connector.getResponse("" +
                "GET / HTTP/1.1\r\n" +
                "Host: localhost\r\n" +
                "Connection: close\r\n" +
                "\r\n");
        assertThat(httpResponse, containsString("HTTP/1.1 200 "));
    }
