    @Test
    public void testIncludeToResourceHandler() throws Exception
    {
        _contextHandler.addServlet(DispatchToResourceServlet.class, "/resourceServlet/*");

        String responses = _connector.getResponse("GET /context/resourceServlet/content.txt?do=include HTTP/1.0\n" + "Host: localhost\n\n");

        // from inside the context.txt file
        Assert.assertNotNull(responses);

        assertTrue(responses.contains("content goes here"));
    }
