    @Test
    public void testSimpleWithContextAsyncContext() throws Exception
    {
        String request = "GET /ctx/servletPath HTTP/1.1\r\n" +
                "Host: localhost\r\n" +
                "Content-Type: application/x-www-form-urlencoded\r\n" +
                "Connection: close\r\n" +
                "\r\n";

        HttpTester.Response response = HttpTester.parseResponse(_connector.getResponse(request));
        assertThat("Response.status", response.getStatus(), is(HttpServletResponse.SC_OK));

        String responseBody = response.getContent();

        assertThat("servlet gets right path", responseBody, containsString("doGet:getServletPath:/servletPath"));
        assertThat("async context gets right path in get", responseBody, containsString("doGet:async:getServletPath:/servletPath"));
        assertThat("async context gets right path in async", responseBody, containsString("async:run:attr:servletPath:/servletPath"));
    }
