    @Test
    public void testExceptionThrownInHandler() throws Exception
    {
        configureServer(new AbstractHandler.ErrorDispatchHandler()
        {
            @Override
            public void doNonErrorHandle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                throw new QuietServletException("TEST handler exception");
            }
        });

        StringBuffer request = new StringBuffer("GET / HTTP/1.0\r\n");
        request.append("Host: localhost\r\n\r\n");

        Socket client = newSocket(_serverURI.getHost(), _serverURI.getPort());
        OutputStream os = client.getOutputStream();

        try (StacklessLogging stackless = new StacklessLogging(HttpChannel.class))
        { 
            Log.getLogger(HttpChannel.class).info("Expecting ServletException: TEST handler exception...");
            os.write(request.toString().getBytes());
            os.flush();

            String response = readResponse(client);
            assertThat(response,Matchers.containsString(" 500 "));
        }
    }
