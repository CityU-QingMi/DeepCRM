    @Test
    public void testSelfRefDeep() throws Exception
    {
        ServletTester tester = new ServletTester();
        tester.setContextPath("/tests");
        tester.addServlet(DispatchServlet.class,"/dispatch/*");
        tester.addServlet(DefaultServlet.class,"/");
        tester.start();

        String selfRefs[] =
        { "/dispatch/forward", "/dispatch/includeS", "/dispatch/includeW", "/dispatch/includeN", };

/**/
/**/
/**/
/**/
/**/
        int nestedDepth = 220;

        for (String selfRef : selfRefs)
        {
            StringBuilder req1 = new StringBuilder();
            req1.append("GET /tests");
            for (int i = 0; i < nestedDepth; i++)
            {
                req1.append(selfRef);
            }

            req1.append("/ HTTP/1.1\n");
            req1.append("Host: tester\n");
            req1.append("Connection: close\n");
            req1.append("\n");

            String response = tester.getResponses(req1.toString());

            StringBuilder msg = new StringBuilder();
            msg.append("Response code on nested \"").append(selfRef).append("\"");
            msg.append(" (depth:").append(nestedDepth).append(")");

            assertFalse(msg + " should not be code 413 (Request Entity Too Large)," +
                    "the nestedDepth in the TestCase is too large (reduce it)",
                    response.startsWith("HTTP/1.1 413 "));

            assertFalse(msg + " should not be code 500.", response.startsWith("HTTP/1.1 500 "));
            assertThat(response,Matchers.startsWith("HTTP/1.1 403 "));
        }
    }
