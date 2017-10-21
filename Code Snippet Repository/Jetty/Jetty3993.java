    @Test
    public void test_StartAsync_OnTimeout_Nothing() throws Exception
    {
        test_StartAsync_OnTimeout(500, event -> {
        });
        String httpResponse = connector.getResponse("" +
                "GET / HTTP/1.1\r\n" +
                "Host: localhost\r\n" +
                "Connection: close\r\n" +
                "\r\n");
        assertThat(httpResponse, containsString("HTTP/1.1 500 "));
    }
