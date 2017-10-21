    @Test
    public void test_StartAsync_OnTimeout_Complete() throws Exception
    {
        test_StartAsync_OnTimeout(500, event ->
        {
            HttpServletResponse response = (HttpServletResponse)event.getAsyncContext().getResponse();
            response.setStatus(HttpStatus.OK_200);
            ServletOutputStream output = response.getOutputStream();
            output.println("COMPLETE");
            event.getAsyncContext().complete();

        });
        String httpResponse = connector.getResponse("" +
                "GET / HTTP/1.1\r\n" +
                "Host: localhost\r\n" +
                "Connection: close\r\n" +
                "\r\n");
        assertThat(httpResponse, containsString("HTTP/1.1 200 "));
        assertThat(httpResponse, containsString("COMPLETE"));
    }
