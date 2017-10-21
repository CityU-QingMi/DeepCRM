    @Test
    public void testForwardWithParam() throws Exception
    {
        _contextHandler.addServlet(ForwardServlet.class, "/ForwardServlet/*");
        _contextHandler.addServlet(EchoURIServlet.class, "/EchoURI/*");

        String expected=
            "HTTP/1.1 200 OK\r\n"+
            "Content-Type: text/plain\r\n"+
            "Content-Length: 54\r\n"+
            "\r\n"+
            "/context\r\n"+
            "/EchoURI\r\n"+
            "/x x\r\n"+
            "/context/EchoURI/x%20x;a=1\r\n";

        String responses = _connector.getResponse("GET /context/ForwardServlet;ignore=true?do=req.echo&uri=EchoURI%2Fx%2520x%3Ba=1%3Fb=2 HTTP/1.0\n\n");

        assertEquals(expected, responses);
    }
