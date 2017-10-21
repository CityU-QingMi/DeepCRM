    @Test
    public void testInclude() throws Exception
    {
        _contextHandler.addServlet(IncludeServlet.class, "/IncludeServlet/*");
        _contextHandler.addServlet(AssertIncludeServlet.class, "/AssertIncludeServlet/*");

        String expected=
            "HTTP/1.1 200 OK\r\n"+
            "\r\n";

        String responses = _connector.getResponse("GET /context/IncludeServlet?do=assertinclude&do=more&test=1 HTTP/1.0\n\n");

        assertEquals(expected, responses);
    }
