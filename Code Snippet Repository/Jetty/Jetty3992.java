    @Test
    public void test_StartAsync_OnTimeout_Throw() throws Exception
    {
        test_StartAsync_OnTimeout(500, event ->
        {
            throw new TestRuntimeException();
        });
        String httpResponse = connector.getResponse("" +
                "GET / HTTP/1.1\r\n" +
                "Host: localhost\r\n" +
                "Connection: close\r\n" +
                "\r\n");
        assertThat(httpResponse, containsString("HTTP/1.1 500 "));
        assertThat(httpResponse, containsString(TestRuntimeException.class.getName()));
    }
