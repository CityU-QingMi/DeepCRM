    @Test
    public void testDispatchAsyncContext_EncodedUrl() throws Exception
    {
        String request = "GET /ctx/test/hello%2fthere?dispatch=true HTTP/1.1\r\n" +
                "Host: localhost\r\n" +
                "Content-Type: application/x-www-form-urlencoded\r\n" +
                "Connection: close\r\n" +
                "\r\n";
        HttpTester.Response response = HttpTester.parseResponse(_connector.getResponse(request));
        assertThat("Response.status", response.getStatus(), is(HttpServletResponse.SC_OK));

        String responseBody = response.getContent();

        // initial values
        assertThat("servlet gets right path", responseBody, containsString("doGet:getServletPath:/test2"));
        assertThat("request uri has correct encoding", responseBody, containsString("doGet:getRequestURI:/ctx/test2/something%2felse"));
        assertThat("request url has correct encoding", responseBody, containsString("doGet:getRequestURL:http://localhost/ctx/test2/something%2felse"));
        assertThat("path info has correct encoding", responseBody, containsString("doGet:getPathInfo:/something/else"));

        // async values
        assertThat("async servlet gets right path", responseBody, containsString("doGet:async:getServletPath:/test2"));
        assertThat("async request uri has correct encoding", responseBody, containsString("doGet:async:getRequestURI:/ctx/test2/something%2felse"));
        assertThat("async request url has correct encoding", responseBody, containsString("doGet:async:getRequestURL:http://localhost/ctx/test2/something%2felse"));
        assertThat("async path info has correct encoding", responseBody, containsString("doGet:async:getPathInfo:/something/else"));

        // async run attributes
        assertThat("async run attr servlet path is original", responseBody, containsString("async:run:attr:servletPath:/test"));
        assertThat("async run attr path info has correct encoding", responseBody, containsString("async:run:attr:pathInfo:/hello/there"));
        assertThat("async run attr query string", responseBody, containsString("async:run:attr:queryString:dispatch=true"));
        assertThat("async run context path", responseBody, containsString("async:run:attr:contextPath:/ctx"));
        assertThat("async run request uri has correct encoding", responseBody, containsString("async:run:attr:requestURI:/ctx/test/hello%2fthere"));
    }
