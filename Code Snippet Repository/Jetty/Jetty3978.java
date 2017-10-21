    @Test
    public void testSimpleAsyncContext() throws Exception
    {
        String request =
                "GET /ctx/servletPath HTTP/1.1\r\n" +
                "Host: localhost\r\n" +
                "Connection: close\r\n" +
                "\r\n";
        HttpTester.Response response = HttpTester.parseResponse(_connector.getResponse(request));
        assertThat("Response.status", response.getStatus(), is(HttpServletResponse.SC_OK));

        String responseBody = response.getContent();

        assertThat(responseBody, containsString("doGet:getServletPath:/servletPath"));
        assertThat(responseBody, containsString("doGet:async:getServletPath:/servletPath"));
        assertThat(responseBody, containsString("async:run:attr:servletPath:/servletPath"));
    }
