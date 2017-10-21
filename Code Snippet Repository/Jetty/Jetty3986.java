    @Test
    public void testStartCompleteThrow() throws Exception
    {
        String request = "GET /ctx/startthrow?complete=true HTTP/1.1\r\n" +
                "Host: localhost\r\n" +
                "Content-Type: application/x-www-form-urlencoded\r\n" +
                "Connection: close\r\n" +
                "\r\n";
        HttpTester.Response response = HttpTester.parseResponse(_connector.getResponse(request));

        assertThat("Response.status", response.getStatus(), is(HttpServletResponse.SC_INTERNAL_SERVER_ERROR));

        String responseBody = response.getContent();
        assertThat(responseBody, containsString("ERROR: /error"));
        assertThat(responseBody, containsString("PathInfo= /IOE"));
        assertThat(responseBody, containsString("EXCEPTION: org.eclipse.jetty.server.QuietServletException: java.io.IOException: Test"));
    }
