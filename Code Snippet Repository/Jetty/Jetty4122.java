    @Test
    public void testErrorException() throws Exception
    {
        try(StacklessLogging stackless = new StacklessLogging(HttpChannel.class))
        {
            String response = _connector.getResponse("GET /fail/exception HTTP/1.0\r\n\r\n");
            assertThat(response,Matchers.containsString("HTTP/1.1 500 Server Error"));
            assertThat(response,Matchers.containsString("ERROR_PAGE: /TestException"));
            assertThat(response,Matchers.containsString("ERROR_CODE: 500"));
            assertThat(response,Matchers.containsString("ERROR_EXCEPTION: javax.servlet.ServletException: java.lang.IllegalStateException"));
            assertThat(response,Matchers.containsString("ERROR_EXCEPTION_TYPE: class javax.servlet.ServletException"));
            assertThat(response,Matchers.containsString("ERROR_SERVLET: org.eclipse.jetty.servlet.ErrorPageTest$FailServlet-"));
            assertThat(response,Matchers.containsString("ERROR_REQUEST_URI: /fail/exception"));
        }
    }
