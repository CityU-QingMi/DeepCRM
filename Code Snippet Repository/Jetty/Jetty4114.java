    @Test
    public void testAsyncFilterWrapTestServlet() throws Exception
    {
        String response = _connector.getResponse("GET /context%20path/test%20servlet/path%20info?async=true&wrap=true HTTP/1.0\n\n");
        assertThat(response,startsWith("HTTP/1.1 200 "));
        assertThat(response,Matchers.containsString("requestURI=/context%20path/test%20servlet/path%20info"));
        assertThat(response,Matchers.containsString("contextPath=/context%20path"));
        assertThat(response,Matchers.containsString("servletPath=/test servlet"));
        assertThat(response,Matchers.containsString("pathInfo=/path info"));
    }
