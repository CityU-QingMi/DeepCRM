    @Test
    public void testServletForward() throws Exception
    {
        _contextHandler.addServlet(DispatchServletServlet.class, "/dispatch/*");
        _contextHandler.addServlet(RogerThatServlet.class, "/roger/*");

        String expected=
            "HTTP/1.1 200 OK\r\n"+
            "Content-Length: 11\r\n"+
            "\r\n"+
            "Roger That!";

        String responses = _connector.getResponse("GET /context/dispatch/test?forward=/roger/that HTTP/1.0\n" + "Host: localhost\n\n");

        assertEquals(expected, responses);
    }
