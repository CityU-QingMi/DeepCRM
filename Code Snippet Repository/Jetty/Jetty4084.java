    @Test
    public void testServletForwardEncodedDotDot() throws Exception
    {
        _contextHandler.addServlet(DispatchServletServlet.class, "/dispatch/*");
        _contextHandler.addServlet(RogerThatServlet.class, "/roger/that");

        String requests="GET /context/dispatch/test?forward=/%252e%252e/roger/that HTTP/1.0\n" + "Host: localhost\n\n";

        String responses = _connector.getResponse(requests);

        assertThat(responses,startsWith("HTTP/1.1 404 "));
    }
