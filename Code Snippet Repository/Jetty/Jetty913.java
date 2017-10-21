    @Test
    public void testPOSTWithParametersWithContent() throws Exception
    {
        final byte[] content = {0, 1, 2, 3};
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
                    response.setContentType("application/octet-stream");
                    IO.copy(request.getInputStream(), response.getOutputStream());
                }
            }
        });

        for (int i = 0; i < 256; ++i)
        {
            ContentResponse response = client.POST(scheme + "://localhost:" + connector.getLocalPort() + "/?b=1")
                    .param(paramName, paramValue)
                    .content(new BytesContentProvider(content))
                    .timeout(5, TimeUnit.SECONDS)
                    .send();

            Assert.assertNotNull(response);
            Assert.assertEquals(200, response.getStatus());
            Assert.assertArrayEquals(content, response.getContent());
        }
    }
