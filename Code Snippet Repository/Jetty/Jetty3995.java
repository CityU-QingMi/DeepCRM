    @Test
    public void test_StartAsync_OnTimeout_SendError_CustomErrorPage() throws Exception
    {
        test_StartAsync_OnTimeout(500, event ->
        {
            AsyncContext asyncContext = event.getAsyncContext();
            HttpServletResponse response = (HttpServletResponse)asyncContext.getResponse();
            response.sendError(HttpStatus.BAD_GATEWAY_502);
            asyncContext.complete();
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
        errorHandler.setServer(server);
        server.setErrorHandler(errorHandler);

        String httpResponse = connector.getResponse("" +
                "GET / HTTP/1.1\r\n" +
                "Host: localhost\r\n" +
                "Connection: close\r\n" +
                "\r\n");
        assertThat(httpResponse, containsString("HTTP/1.1 502 "));
        assertThat(httpResponse, containsString("CUSTOM"));
    }
