    @Test
    public void testProxyWithBigRequestContentConsumed() throws Exception
    {
        final byte[] content = new byte[128 * 1024];
        new Random().nextBytes(content);
        startServer(new HttpServlet()
        {
            @Override
            protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
            {
                if (req.getHeader("Via") != null)
                    resp.addHeader(PROXIED_HEADER, "true");
                InputStream input = req.getInputStream();
                int index = 0;
                
                byte[] buffer = new byte[16*1024];
                while (true)
                {
                    int value = input.read(buffer);
                    if (value < 0)
                        break;
                    for (int i=0;i<value;i++)
                    {
                        Assert.assertEquals("Content mismatch at index=" + index, content[index] & 0xFF, buffer[i] & 0xFF);
                        ++index;
                    }
                }
            }
        });
        startProxy();
        startClient();

        ContentResponse response = client.newRequest("localhost", serverConnector.getLocalPort())
                .method(HttpMethod.POST)
                .content(new BytesContentProvider(content))
                .timeout(5, TimeUnit.SECONDS)
                .send();

        Assert.assertEquals(200, response.getStatus());
        Assert.assertTrue(response.getHeaders().containsKey(PROXIED_HEADER));
    }
