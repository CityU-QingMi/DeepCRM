    @Test
    public void testResponseHeadersAreNotRemoved() throws Exception
    {
        startServer(new EmptyHttpServlet());
        startProxy();
        proxyContext.stop();
        final String headerName = "X-Test";
        final String headerValue = "test-value";
        proxyContext.addFilter(new FilterHolder(new Filter()
        {
            @Override
            public void init(FilterConfig filterConfig) throws ServletException
            {
            }

            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
            {
                ((HttpServletResponse)response).addHeader(headerName, headerValue);
                chain.doFilter(request, response);
            }

            @Override
            public void destroy()
            {
            }
        }), "/*", EnumSet.of(DispatcherType.REQUEST));
        proxyContext.start();
        startClient();

        ContentResponse response = client.newRequest("localhost", serverConnector.getLocalPort())
                .timeout(5, TimeUnit.SECONDS)
                .send();

        Assert.assertEquals(200, response.getStatus());
        Assert.assertEquals(headerValue, response.getHeaders().get(headerName));
    }
