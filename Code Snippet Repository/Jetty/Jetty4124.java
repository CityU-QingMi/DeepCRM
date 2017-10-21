    @Test
    public void testGlobalErrorException() throws Exception
    {
        try(StacklessLogging stackless = new StacklessLogging(HttpChannel.class))
        {
            String response = _connector.getResponse("GET /fail/global?code=NAN HTTP/1.0\r\n\r\n");
            assertThat(response,Matchers.containsString("HTTP/1.1 500 Server Error"));
            assertThat(response,Matchers.containsString("ERROR_PAGE: /GlobalErrorPage"));
            assertThat(response,Matchers.containsString("ERROR_CODE: 500"));
            assertThat(response,Matchers.containsString("ERROR_EXCEPTION: java.lang.NumberFormatException: For input string: \"NAN\""));
            assertThat(response,Matchers.containsString("ERROR_EXCEPTION_TYPE: class java.lang.NumberFormatException"));
            assertThat(response,Matchers.containsString("ERROR_SERVLET: org.eclipse.jetty.servlet.ErrorPageTest$FailServlet-"));
            assertThat(response,Matchers.containsString("ERROR_REQUEST_URI: /fail/global"));
        }
    }
