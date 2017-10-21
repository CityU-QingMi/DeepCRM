    @Test
    public void testInterruptedRequest() throws Exception
    {
        final AtomicBoolean fourBytesRead = new AtomicBoolean(false);
        final AtomicBoolean earlyEOFException = new AtomicBoolean(false);
        configureServer(new AbstractHandler.ErrorDispatchHandler()
        {
            @Override
            public void doNonErrorHandle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                int contentLength = request.getContentLength();
                ServletInputStream inputStream = request.getInputStream();
                for (int i = 0; i < contentLength; i++)
                {
                    try
                    {
                        inputStream.read();
                    }
                    catch (EofException e)
                    {
                        earlyEOFException.set(true);
                        throw new QuietServletException(e);
                    }
                    if (i == 3)
                        fourBytesRead.set(true);
                }
            }
        });

        StringBuffer request = new StringBuffer("GET / HTTP/1.0\n");
        request.append("Host: localhost\n");
        request.append("Content-length: 6\n\n");
        request.append("foo");

        Socket client = newSocket(_serverURI.getHost(), _serverURI.getPort());
        OutputStream os = client.getOutputStream();

        os.write(request.toString().getBytes());
        os.flush();
        client.shutdownOutput();
        String response = readResponse(client);
        client.close();

        assertThat("response contains 500", response, Matchers.containsString(" 500 "));
        assertThat("The 4th byte (-1) has not been passed to the handler", fourBytesRead.get(), is(false));
        assertThat("EofException has been caught", earlyEOFException.get(), is(true));
    }
