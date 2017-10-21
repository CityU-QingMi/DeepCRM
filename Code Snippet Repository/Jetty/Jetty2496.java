    @Test
    public void testProxyWithBigRequestContentIgnored() throws Exception
    {
        startServer(new HttpServlet()
        {
            @Override
            protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
            {
                try
                {
                    // Give some time to the proxy to
                    // upload the content to the server.
                    Thread.sleep(1000);

                    if (req.getHeader("Via") != null)
                        resp.addHeader(PROXIED_HEADER, "true");
                }
                catch (InterruptedException x)
                {
                    throw new InterruptedIOException();
                }
            }
        });
        startProxy();
        startClient();

        byte[] content = new byte[128 * 1024];
        ContentResponse response = client.newRequest("localhost", serverConnector.getLocalPort())
                .method(HttpMethod.POST)
                .content(new BytesContentProvider(content))
                .timeout(5, TimeUnit.SECONDS)
                .send();

        Assert.assertEquals(200, response.getStatus());
        Assert.assertTrue(response.getHeaders().containsKey(PROXIED_HEADER));
    }
