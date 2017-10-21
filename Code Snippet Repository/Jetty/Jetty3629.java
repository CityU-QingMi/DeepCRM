    @Test
    public void testBadUtf8ParamExtraction() throws Exception
    {
        _handler._checker = new RequestTester()
        {
            @Override
            public boolean check(HttpServletRequest request,HttpServletResponse response)
            {
                try
                {
                    // This throws an exception if attempted
                    request.getParameter("param");
                    return false;
                }
                catch(BadMessageException e)
                {
                    // Should still be able to get the raw query.
                    String rawQuery = request.getQueryString();
                    return rawQuery.equals("param=aaa%E7bbb");
                }
            }
        };

        //Send a request with query string with illegal hex code to cause
        //an exception parsing the params
        String request="GET /?param=aaa%E7bbb HTTP/1.1\r\n"+
        "Host: whatever\r\n"+
        "Content-Type: text/html;charset=utf8\n"+
        "Connection: close\n"+
        "\n";

        LOG.info("Expecting NotUtf8Exception in state 36...");
        String responses=_connector.getResponse(request);
        assertThat(responses,startsWith("HTTP/1.1 200"));
    }
