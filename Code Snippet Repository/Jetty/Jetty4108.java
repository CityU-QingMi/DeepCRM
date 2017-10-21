    @Test
    public void testIncludeThenForward() throws Exception
    {
        _contextHandler.addServlet(IncludeServlet.class, "/IncludeServlet/*");
        _contextHandler.addServlet(ForwardServlet.class, "/ForwardServlet/*");
        _contextHandler.addServlet(AssertIncludeForwardServlet.class, "/AssertIncludeForwardServlet/*");

        String expected=
            "HTTP/1.1 200 OK\r\n"+
            "\r\n";

        String responses = _connector.getResponse("GET /context/IncludeServlet/includepath?do=forward HTTP/1.0\n\n");

        assertEquals(expected, responses);
    }
