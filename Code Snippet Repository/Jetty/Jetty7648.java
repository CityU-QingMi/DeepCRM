    @Test
    public void testSelfRefForwardDenialOfService() throws Exception
    {
        ServletTester tester = new ServletTester();
        tester.setContextPath("/tests");

        ServletHolder dispatch = tester.addServlet(DispatchServlet.class,"/dispatch/*");
        tester.addServlet(DefaultServlet.class,"/");
        tester.start();

        StringBuilder req1 = new StringBuilder();
        req1.append("GET /tests/dispatch/includeN/").append(dispatch.getName()).append(" HTTP/1.1\n");
        req1.append("Host: tester\n");
        req1.append("Connection: close\n");
        req1.append("\n");

        String response = tester.getResponses(req1.toString());

        String msg = "Response code on SelfRefDoS";

        assertFalse(msg + " should not be code 500.",response.startsWith("HTTP/1.1 500 "));
        assertTrue(msg + " should return error code 403 (Forbidden)", response.startsWith("HTTP/1.1 403 "));
    }
