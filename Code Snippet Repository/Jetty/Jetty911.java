    @Test
    public void testPOSTWithQueryString() throws Exception
    {
        final String paramName = "a";
        final String paramValue = "\u20AC";
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, org.eclipse.jetty.server.Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                String value = request.getParameter(paramName);
                if (paramValue.equals(value))
                {
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("text/plain");
                    response.getOutputStream().print(value);
                }
            }
        });

        String uri = scheme + "://localhost:" + connector.getLocalPort() +
                "/?" + paramName + "=" + URLEncoder.encode(paramValue, "UTF-8");
        ContentResponse response = client.POST(uri)
                .timeout(5, TimeUnit.SECONDS)
                .send();

        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
        Assert.assertEquals(paramValue, new String(response.getContent(), "UTF-8"));
    }
