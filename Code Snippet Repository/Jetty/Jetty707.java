    @Test
    public void testSimpleField() throws Exception
    {
        String name = "field";
        String value = "value";
        start(new AbstractMultiPartHandler()
        {
            @Override
            protected void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
            {
                Collection<Part> parts = request.getParts();
                Assert.assertEquals(1, parts.size());
                Part part = parts.iterator().next();
                Assert.assertEquals(name, part.getName());
                Assert.assertEquals(value, IO.toString(part.getInputStream()));
            }
        });

        MultiPartContentProvider multiPart = new MultiPartContentProvider();
        multiPart.addFieldPart(name, new StringContentProvider(value), null);
        multiPart.close();
        ContentResponse response = client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .method(HttpMethod.POST)
                .content(multiPart)
                .send();

        Assert.assertEquals(200, response.getStatus());
    }
