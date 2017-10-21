    @Test
    public void testHostHeaderPreserved() throws Exception
    {
        startServer(new HttpServlet()
        {
            @Override
            protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
            {
                Assert.assertEquals("localhost", request.getServerName());
                Assert.assertEquals(proxyConnector.getLocalPort(), request.getServerPort());
            }
        });
        startProxy(new HashMap<String, String>() {{ put("preserveHost", "true"); }});
        startClient();

        ContentResponse response = client.newRequest("localhost", proxyConnector.getLocalPort()).send();
        Assert.assertEquals(200, response.getStatus());
    }
