    @Test
    public void testForward() throws Exception
    {
        _contextHandler.addServlet(ForwardServlet.class, "/ForwardServlet/*");
        _contextHandler.addServlet(AssertForwardServlet.class, "/AssertForwardServlet/*");

        String expected=
            "HTTP/1.1 200 OK\r\n"+
            "Content-Type: text/html\r\n"+
            "Content-Length: 0\r\n"+
            "\r\n";

        String responses = _connector.getResponse("GET /context/ForwardServlet?do=assertforward&do=more&test=1 HTTP/1.0\n\n");

        assertEquals(expected, responses);
    }
