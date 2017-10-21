    @Test
    public void test_StartAsync_Throw_OnError_Nothing() throws Exception
    {
        test_StartAsync_Throw_OnError(event -> {});
        String httpResponse = connector.getResponse("" +
                "GET /ctx/path HTTP/1.1\r\n" +
                "Host: localhost\r\n" +
                "Connection: close\r\n" +
                "\r\n");
        assertThat(httpResponse, containsString("HTTP/1.1 500 "));
        assertThat(httpResponse, containsString(TestRuntimeException.class.getName()));
    }
