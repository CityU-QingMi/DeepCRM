    private void testProxyResponseWriteFails(final int writeCount) throws Exception
    {
        startServer(new HttpServlet()
        {
            @Override
            protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
            {
                ServletOutputStream output = response.getOutputStream();
                output.write(new byte[512]);
                output.flush();
                output.write(new byte[512]);
            }
        });
        startProxy(new AsyncMiddleManServlet()
        {
            private int count;

            @Override
            protected void writeProxyResponseContent(ServletOutputStream output, ByteBuffer content) throws IOException
            {
                if (++count < writeCount)
                    super.writeProxyResponseContent(output, content);
                else
                    throw new IOException("explicitly_thrown_by_test");
            }
        });
        startClient();

        ContentResponse response = client.newRequest("localhost", serverConnector.getLocalPort())
                .timeout(5, TimeUnit.SECONDS)
                .send();

        Assert.assertEquals(502, response.getStatus());
    }
