    @Test
    public void testParamExtraction_BadSequence() throws Exception
    {
        _handler._checker = new RequestTester()
        {
            @Override
            public boolean check(HttpServletRequest request,HttpServletResponse response)
            {
                Map<String, String[]> map = request.getParameterMap();
                // should have thrown a BadMessageException
                return false;
            }
        };

        //Send a request with query string with illegal hex code to cause
        //an exception parsing the params
        String request="GET /?test_%e0%x8%81=missing HTTP/1.1\r\n"+
                "Host: whatever\r\n"+
                "Content-Type: text/html;charset=utf8\n"+
                "Connection: close\n"+
                "\n";

        String responses=_connector.getResponse(request);
        assertThat("Responses", responses, startsWith("HTTP/1.1 400"));
    }
