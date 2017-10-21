    @Test
    public void test_StartAsync_OnComplete_Throw() throws Exception
    {
        ServletContextHandler context = new ServletContextHandler();
        context.addServlet(new ServletHolder(new HttpServlet()
        {
            @Override
            protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
            {
                AsyncContext asyncContext = request.startAsync();
                asyncContext.setTimeout(0);
                asyncContext.addListener(new AsyncListenerAdapter()
                {
                    @Override
                    public void onComplete(AsyncEvent event) throws IOException
                    {
                        throw new TestRuntimeException();
                    }
                });
                response.getOutputStream().print("DATA");
                asyncContext.complete();
            }
        }), "/*");

        startServer(context);

        String httpResponse = connector.getResponse("" +
                "GET / HTTP/1.1\r\n" +
                "Host: localhost\r\n" +
                "Connection: close\r\n" +
                "\r\n");
        assertThat(httpResponse, containsString("HTTP/1.1 200 "));
        assertThat(httpResponse, containsString("DATA"));
    }
