    @Test
    public void testDispatchRequestResponse() throws Exception
    {
        String request = "GET /ctx/forward?dispatchRequestResponse=true HTTP/1.1\r\n" +
                "Host: localhost\r\n" +
                "Content-Type: application/x-www-form-urlencoded\r\n" +
                "Connection: close\r\n" +
                "\r\n";

        String responseString = _connector.getResponse(request);

        HttpTester.Response response = HttpTester.parseResponse(responseString);
        assertThat("Response.status", response.getStatus(), is(HttpServletResponse.SC_OK));

        String responseBody = response.getContent();

        assertThat("!AsyncDispatchingServlet", responseBody, containsString("Dispatched back to AsyncDispatchingServlet"));
    }
