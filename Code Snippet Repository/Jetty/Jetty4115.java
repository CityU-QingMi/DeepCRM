    @Test
    public void testAsyncServletTestServlet() throws Exception
    {
        String response = _connector.getResponse("GET /context%20path/async%20servlet/path%20info HTTP/1.0\n\n");
        assertThat(response,startsWith("HTTP/1.1 200 "));
        assertThat(response,Matchers.containsString("requestURI=/context%20path/test servlet/path info"));
        assertThat(response,Matchers.containsString("contextPath=/context%20path"));
        assertThat(response,Matchers.containsString("servletPath=/test servlet"));
        assertThat(response,Matchers.containsString("pathInfo=/path info"));
    }
