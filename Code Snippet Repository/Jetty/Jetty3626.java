    @Test
    public void testEmptyHeaders() throws Exception
    {
        _handler._checker = new RequestTester()
        {
            @Override
            public boolean check(HttpServletRequest request,HttpServletResponse response)
            {
                assertNotNull(request.getLocale());
                assertTrue(request.getLocales().hasMoreElements()); // Default locale
                assertEquals("",request.getContentType());
                assertNull(request.getCharacterEncoding());
                assertEquals(0,request.getQueryString().length());
                assertEquals(-1,request.getContentLength());
                assertNull(request.getCookies());
                assertEquals("",request.getHeader("Name"));
                assertTrue(request.getHeaders("Name").hasMoreElements()); // empty
                try
                {
                    request.getDateHeader("Name");
                    assertTrue(false);
                }
                catch(IllegalArgumentException e)
                {
                    
                }
                assertEquals(-1,request.getDateHeader("Other"));
                return true;
            }
        };

        String request="GET /? HTTP/1.1\r\n"+
        "Host: whatever\r\n"+
        "Connection: close\n"+
        "Content-Type: \n"+
        "Accept-Language: \n"+
        "Cookie: \n"+
        "Name: \n"+
        "\n";

        String responses=_connector.getResponse(request);
        assertTrue(responses.startsWith("HTTP/1.1 200"));
    }
