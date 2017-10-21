    @Test
    public void testProxyWithRequestContentAndResponseContent() throws Exception
    {
        startServer(new HttpServlet()
        {
            @Override
            protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
            {
                if (req.getHeader("Via") != null)
                    resp.addHeader(PROXIED_HEADER, "true");
                IO.copy(req.getInputStream(), resp.getOutputStream());
            }
        });
        startProxy();
        startClient();

        byte[] content = new byte[1024];
        new Random().nextBytes(content);
        ContentResponse response = client.newRequest("localhost", serverConnector.getLocalPort())
                .method(HttpMethod.POST)
                .content(new BytesContentProvider(content))
                .timeout(5, TimeUnit.SECONDS)
                .send();

        Assert.assertEquals(200, response.getStatus());
        Assert.assertTrue(response.getHeaders().containsKey(PROXIED_HEADER));
        Assert.assertArrayEquals(content, response.getContent());
    }
