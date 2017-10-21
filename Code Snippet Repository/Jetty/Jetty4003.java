    @Test
    public void test_StartAsync_Throw_OnError_SendError_CustomErrorPage() throws Exception
    {
        test_StartAsync_Throw_OnError(event ->
        {
            HttpServletResponse response = (HttpServletResponse)event.getAsyncContext().getResponse();
            response.sendError(HttpStatus.BAD_GATEWAY_502);
        });

        // Add a custom error page.
        ErrorHandler errorHandler = new ErrorHandler()
        {
            @Override
            protected void writeErrorPageMessage(HttpServletRequest request, Writer writer, int code, String message, String uri) throws IOException
            {
                writer.write("CUSTOM\n");
                super.writeErrorPageMessage(request,writer,code,message,uri);
            }
            
        };
        server.setErrorHandler(errorHandler);

        String httpResponse = connector.getResponse("" +
                "GET /ctx/path HTTP/1.1\r\n" +
                "Host: localhost\r\n" +
                "Connection: close\r\n" +
                "\r\n", 10, TimeUnit.MINUTES);
        assertThat(httpResponse, containsString("HTTP/1.1 502 "));
        assertThat(httpResponse, containsString("CUSTOM"));
    }
