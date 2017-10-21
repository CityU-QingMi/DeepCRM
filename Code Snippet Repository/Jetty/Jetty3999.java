    @Test
    public void test_StartAsync_Throw_OnError_Complete() throws Exception
    {
        test_StartAsync_Throw_OnError(event ->
        {
            HttpServletResponse response = (HttpServletResponse)event.getAsyncContext().getResponse();
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR_500);
            ServletOutputStream output = response.getOutputStream();
            output.println(event.getThrowable().getClass().getName());
            if (event.getThrowable().getCause()!=null)
                output.println(event.getThrowable().getCause().getClass().getName());
            output.println("COMPLETE");
            event.getAsyncContext().complete();
        });
        String httpResponse = connector.getResponse("" +
                "GET /ctx/path HTTP/1.1\r\n" +
                "Host: localhost\r\n" +
                "Connection: close\r\n" +
                "\r\n");
        assertThat(httpResponse, containsString("HTTP/1.1 500 "));
        assertThat(httpResponse, containsString(TestRuntimeException.class.getName()));
        assertThat(httpResponse, containsString("COMPLETE"));
    }
