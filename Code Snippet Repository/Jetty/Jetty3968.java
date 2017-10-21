    @Test
    public void testDispatchAsyncContextEncodedPathAndQueryString() throws Exception
    {
        String request = "GET /ctx/path%20with%20spaces/servletPath?dispatch=true&queryStringWithEncoding=space%20space HTTP/1.1\r\n" +
                "Host: localhost\r\n" +
                "Content-Type: application/x-www-form-urlencoded\r\n" +
                "Connection: close\r\n" +
                "\r\n";
        HttpTester.Response response = HttpTester.parseResponse(_connector.getResponse(request));
        assertThat("Response.status", response.getStatus(), is(HttpServletResponse.SC_OK));

        String responseBody = response.getContent();

        assertThat("servlet gets right path", responseBody, containsString("doGet:getServletPath:/servletPath2"));
        assertThat("async context gets right path in get", responseBody, containsString("doGet:async:getServletPath:/servletPath2"));
        assertThat("servlet path attr is original", responseBody, containsString("async:run:attr:servletPath:/path with spaces/servletPath"));
        assertThat("path info attr is correct", responseBody, containsString("async:run:attr:pathInfo:null"));
        assertThat("query string attr is correct", responseBody, containsString("async:run:attr:queryString:dispatch=true&queryStringWithEncoding=space%20space"));
        assertThat("context path attr is correct", responseBody, containsString("async:run:attr:contextPath:/ctx"));
        assertThat("request uri attr is correct", responseBody, containsString("async:run:attr:requestURI:/ctx/path%20with%20spaces/servletPath"));
    }
