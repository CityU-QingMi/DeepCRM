    private void testGETWithResponseContent(int length, final long delay) throws Exception
    {
        final byte[] data = new byte[length];
        new Random().nextBytes(data);

        final String path = "/foo/index.php";
        prepare(new HttpServlet()
        {
            @Override
            protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
            {
                Assert.assertTrue(request.getRequestURI().endsWith(path));
                response.setContentLength(data.length);
                response.getOutputStream().write(data);
            }
        });

        Request request = client.newRequest("localhost", httpConnector.getLocalPort())
                .onResponseContentAsync((response, content, callback) ->
                {
                    try
                    {
                        if (delay > 0)
                            TimeUnit.MILLISECONDS.sleep(delay);
                        callback.succeeded();
                    }
                    catch (InterruptedException x)
                    {
                        callback.failed(x);
                    }
                })
                .path(path);
        FutureResponseListener listener = new FutureResponseListener(request, length);
        request.send(listener);

        ContentResponse response = listener.get(30, TimeUnit.SECONDS);

        Assert.assertEquals(200, response.getStatus());
        Assert.assertArrayEquals(data, response.getContent());
    }
