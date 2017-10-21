    @Test
    public void testEmptyMultiPart() throws Exception
    {
        start(new AbstractMultiPartHandler()
        {
            @Override
            protected void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
            {
                Collection<Part> parts = request.getParts();
                Assert.assertEquals(0, parts.size());
            }
        });

        MultiPartContentProvider multiPart = new MultiPartContentProvider();
        multiPart.close();
        ContentResponse response = client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .method(HttpMethod.POST)
                .content(multiPart)
                .send();

        Assert.assertEquals(200, response.getStatus());
    }
