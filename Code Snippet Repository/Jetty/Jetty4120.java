    @Test
    public void testSendErrorClosedResponse() throws Exception
    {
        String response = _connector.getResponse("GET /fail-closed/ HTTP/1.0\r\n\r\n");
        System.out.println(response);
        assertThat(response,Matchers.containsString("HTTP/1.1 599 599"));
        assertThat(response,Matchers.containsString("DISPATCH: ERROR"));
        assertThat(response,Matchers.containsString("ERROR_PAGE: /599"));
        assertThat(response,Matchers.containsString("ERROR_CODE: 599"));
        assertThat(response,Matchers.containsString("ERROR_EXCEPTION: null"));
        assertThat(response,Matchers.containsString("ERROR_EXCEPTION_TYPE: null"));
        assertThat(response,Matchers.containsString("ERROR_SERVLET: org.eclipse.jetty.servlet.ErrorPageTest$FailClosedServlet-"));
        assertThat(response,Matchers.containsString("ERROR_REQUEST_URI: /fail-closed/"));
        
        assertThat(response,not(containsString("This shouldn't be seen")));
    }
