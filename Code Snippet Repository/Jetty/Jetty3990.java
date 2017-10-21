    @Test
    public void testDispatchAsyncContext_SelfEncodedUrl() throws Exception
    {
        String request = "GET /ctx/self/hello%2fthere?dispatch=true HTTP/1.1\r\n" +
                "Host: localhost\r\n" +
                "Content-Type: application/x-www-form-urlencoded\r\n" +
                "Connection: close\r\n" +
                "\r\n";
        HttpTester.Response response = HttpTester.parseResponse(_connector.getResponse(request));
        assertThat("Response.status", response.getStatus(), is(HttpServletResponse.SC_OK));

        String responseBody = response.getContent();

        assertThat("servlet request uri initial", responseBody, containsString("doGet.REQUEST.requestURI:/ctx/self/hello%2fthere"));
        assertThat("servlet request uri async", responseBody, containsString("doGet.ASYNC.requestURI:/ctx/self/hello%2fthere"));
    }
