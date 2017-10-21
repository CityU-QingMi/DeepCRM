    @Test
    public void testForwardThenInclude() throws Exception
    {
        _contextHandler.addServlet(ForwardServlet.class, "/ForwardServlet/*");
        _contextHandler.addServlet(IncludeServlet.class, "/IncludeServlet/*");
        _contextHandler.addServlet(AssertForwardIncludeServlet.class, "/AssertForwardIncludeServlet/*");

        String expected=
            "HTTP/1.1 200 OK\r\n"+
            "\r\n";

        String responses = _connector.getResponse("GET /context/ForwardServlet/forwardpath?do=include HTTP/1.0\n\n");

        assertEquals(expected, responses);
    }
