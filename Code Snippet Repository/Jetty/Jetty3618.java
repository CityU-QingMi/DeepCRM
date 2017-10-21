    @Test
    public void testParamExtraction() throws Exception
    {
        _handler._checker = new RequestTester()
        {
            @Override
            public boolean check(HttpServletRequest request,HttpServletResponse response)
            {
                try
                {
                    // do the parse
                    request.getParameterMap();
                    return false;
                }
                catch(BadMessageException e)
                {
                    // Should be able to retrieve the raw query
                    String rawQuery = request.getQueryString();
                    return rawQuery.equals("param=aaa%ZZbbb&other=value");
                }
            }
        };

        //Send a request with query string with illegal hex code to cause
        //an exception parsing the params
        String request="GET /?param=aaa%ZZbbb&other=value HTTP/1.1\r\n"+
        "Host: whatever\r\n"+
        "Content-Type: text/html;charset=utf8\n"+
        "Connection: close\n"+
        "\n";

        String responses=_connector.getResponse(request);
        assertTrue(responses.startsWith("HTTP/1.1 200"));
    }
