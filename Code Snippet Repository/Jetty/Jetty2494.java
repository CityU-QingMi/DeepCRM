    @Test
    public void testProxyWithResponseContent() throws Exception
    {
        final byte[] content = new byte[1024];
        new Random().nextBytes(content);
        startServer(new HttpServlet()
        {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
            {
                if (req.getHeader("Via") != null)
                    resp.addHeader(PROXIED_HEADER, "true");
                resp.getOutputStream().write(content);
            }
        });
        startProxy();
        startClient();

        ContentResponse[] responses = new ContentResponse[10];
        for (int i = 0; i < 10; ++i)
        {
            // Request is for the target server
            responses[i] = client.newRequest("localhost", serverConnector.getLocalPort())
                    .timeout(5, TimeUnit.SECONDS)
                    .send();
        }

        for (int i = 0; i < 10; ++i)
        {
            Assert.assertEquals(200, responses[i].getStatus());
            Assert.assertTrue(responses[i].getHeaders().containsKey(PROXIED_HEADER));
            Assert.assertArrayEquals(content, responses[i].getContent());
        }
    }
