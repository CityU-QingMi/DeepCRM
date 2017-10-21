    @Test
    public void testErrorCode() throws Exception
    {
        String response = _connector.getResponse("GET /fail/code?code=599 HTTP/1.0\r\n\r\n");
        assertThat(response,Matchers.containsString("HTTP/1.1 599 599"));
        assertThat(response,Matchers.containsString("ERROR_PAGE: /599"));
        assertThat(response,Matchers.containsString("ERROR_CODE: 599"));
        assertThat(response,Matchers.containsString("ERROR_EXCEPTION: null"));
        assertThat(response,Matchers.containsString("ERROR_EXCEPTION_TYPE: null"));
        assertThat(response,Matchers.containsString("ERROR_SERVLET: org.eclipse.jetty.servlet.ErrorPageTest$FailServlet-"));
        assertThat(response,Matchers.containsString("ERROR_REQUEST_URI: /fail/code"));
    }
