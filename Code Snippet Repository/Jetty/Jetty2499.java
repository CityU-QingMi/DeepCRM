    @Test
    public void testHostHeaderUpdatedWhenSentToServer() throws Exception
    {
        startServer(new HttpServlet()
        {
            @Override
            protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
            {
                Assert.assertEquals("127.0.0.1", request.getServerName());
                Assert.assertEquals(serverConnector.getLocalPort(), request.getServerPort());
            }
        });
        startProxy(null);
        startClient();

        ContentResponse response = client.newRequest("localhost", proxyConnector.getLocalPort()).send();
        Assert.assertEquals(200, response.getStatus());
    }
