    @Test
    public void testStartFlushCompleteThrow() throws Exception
    {
        try(StacklessLogging ignore = new StacklessLogging(HttpChannel.class))
        {
            String request = "GET /ctx/startthrow?flush=true&complete=true HTTP/1.1\r\n" + 
                    "Host: localhost\r\n" + 
                    "Content-Type: application/x-www-form-urlencoded\r\n" + 
                    "Connection: close\r\n" + 
                    "\r\n";
            HttpTester.Response response = HttpTester.parseResponse(_connector.getResponse(request));
            assertThat("Response.status", response.getStatus(), is(HttpServletResponse.SC_OK));

            String responseBody = response.getContent();

            assertThat("error servlet", responseBody, containsString("completeBeforeThrow"));
        }
    }
