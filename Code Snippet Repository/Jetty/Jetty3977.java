    @Test
    public void testBadExpire() throws Exception
    {
        String request = "GET /ctx/badexpire HTTP/1.1\r\n" +
                "Host: localhost\r\n" +
                "Content-Type: application/x-www-form-urlencoded\r\n" +
                "Connection: close\r\n" +
                "\r\n";
        HttpTester.Response response = HttpTester.parseResponse(_connector.getResponse(request));
        assertThat("Response.status", response.getStatus(), is(HttpServletResponse.SC_INTERNAL_SERVER_ERROR));

        String responseBody = response.getContent();

        assertThat("error servlet", responseBody, containsString("ERROR: /error"));
        assertThat("error servlet", responseBody, containsString("PathInfo= /500"));
        assertThat("error servlet", responseBody, containsString("EXCEPTION: java.lang.RuntimeException: TEST"));
    }
