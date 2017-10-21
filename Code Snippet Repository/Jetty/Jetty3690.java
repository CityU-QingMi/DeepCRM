    @Test(timeout = 5000)
    public void testAccess() throws Exception
    {
        HttpTester.Request request = HttpTester.newRequest();

        request.setMethod("GET");
        request.setHeader("Host", "tester");
        request.setURI(requestURI);

        String responseString = localConnector.getResponse(BufferUtil.toString(request.generate()));
        assertThat("Response status code", responseString, startsWith("HTTP/1.1 " + expectedResponseStatus + " "));
        assertThat("Response Content-Type", responseString, containsString("\nContent-Type: " + expectedResponseContentType));
        assertThat("Response", responseString, containsString(expectedResponseContentContains));
    }
