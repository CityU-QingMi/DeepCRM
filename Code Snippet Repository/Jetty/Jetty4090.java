    @Test
    public void testForwardFilterToRogerServlet() throws Exception
    {
        _contextHandler.addServlet(RogerThatServlet.class, "/*");
        _contextHandler.addServlet(ReserveEchoServlet.class,"/recho/*");
        _contextHandler.addServlet(EchoServlet.class, "/echo/*");
        _contextHandler.addFilter(ForwardFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));

        String rogerResponse = _connector.getResponse("GET /context/ HTTP/1.0\n" + "Host: localhost\n\n");

        String echoResponse = _connector.getResponse("GET /context/foo?echo=echoText HTTP/1.0\n" + "Host: localhost\n\n");

        String rechoResponse = _connector.getResponse("GET /context/?echo=echoText HTTP/1.0\n" + "Host: localhost\n\n");

        assertTrue(rogerResponse.contains("Roger That!"));
        assertTrue(echoResponse.contains("echoText"));
        assertTrue(rechoResponse.contains("txeTohce"));
    }
