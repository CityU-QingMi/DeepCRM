    @Test
    public void testWrappedForwardToResourceHandler() throws Exception
    {
        _contextHandler.addServlet(DispatchToResourceServlet.class, "/resourceServlet/*");

        String responses = _connector.getResponse("GET /context/resourceServlet/content.txt?do=forward&wrapped=true HTTP/1.0\n" + "Host: localhost\n\n");

        // from inside the context.txt file
        assertTrue(responses.contains("content goes here"));
    }
