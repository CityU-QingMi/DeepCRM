    @Test
    public void test_IPv6_Host() throws Exception
    {
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, org.eclipse.jetty.server.Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                response.setContentType("text/plain");
                response.getOutputStream().print(request.getHeader("Host"));
            }
        });

        URI uri = URI.create(scheme + "://[::1]:" + connector.getLocalPort() + "/path");
        ContentResponse response = client.newRequest(uri)
            .method(HttpMethod.PUT)
            .timeout(5, TimeUnit.SECONDS)
            .send();

        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
        Assert.assertThat(new String(response.getContent(), StandardCharsets.ISO_8859_1),Matchers.startsWith("[::1]:"));
    }
