    @Test
    public void test_StartAsync_Throw_OnError_SendError() throws Exception
    {
        test_StartAsync_Throw_OnError(event ->
        {
            HttpServletResponse response = (HttpServletResponse)event.getAsyncContext().getResponse();
            response.sendError(HttpStatus.BAD_GATEWAY_502);
        });
        String httpResponse = connector.getResponse("" +
                "GET /ctx/path HTTP/1.1\r\n" +
                "Host: localhost\r\n" +
                "Connection: close\r\n" +
                "\r\n");
        assertThat(httpResponse, containsString("HTTP/1.1 502 "));
        assertThat(httpResponse, containsString(TestRuntimeException.class.getName()));
    }
