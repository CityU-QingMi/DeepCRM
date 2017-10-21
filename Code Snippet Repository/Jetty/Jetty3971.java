    @Test
    public void testDispatch() throws Exception
    {
        String request = 
            "GET /ctx/forward HTTP/1.1\r\n" + 
            "Host: localhost\r\n" + 
            "Content-Type: application/x-www-form-urlencoded\r\n" + 
            "Connection: close\r\n" + 
            "\r\n";

        String responseString = _connector.getResponse(request);
        HttpTester.Response response = HttpTester.parseResponse(responseString);
        assertThat("Response.status", response.getStatus(), is(HttpServletResponse.SC_OK));

        String responseBody = response.getContent();
        assertThat("!ForwardingServlet", responseBody, containsString("Dispatched back to ForwardingServlet"));
    }
