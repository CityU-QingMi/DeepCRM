    @Test
    public void testGlobalErrorCode() throws Exception
    {
        String response = _connector.getResponse("GET /fail/global?code=598 HTTP/1.0\r\n\r\n");
        assertThat(response,Matchers.containsString("HTTP/1.1 598 598"));
        assertThat(response,Matchers.containsString("ERROR_PAGE: /GlobalErrorPage"));
        assertThat(response,Matchers.containsString("ERROR_CODE: 598"));
        assertThat(response,Matchers.containsString("ERROR_EXCEPTION: null"));
        assertThat(response,Matchers.containsString("ERROR_EXCEPTION_TYPE: null"));
        assertThat(response,Matchers.containsString("ERROR_SERVLET: org.eclipse.jetty.servlet.ErrorPageTest$FailServlet-"));
        assertThat(response,Matchers.containsString("ERROR_REQUEST_URI: /fail/global"));
    }
