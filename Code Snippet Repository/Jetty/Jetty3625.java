    @Test
    public void testParamExtraction_Timeout() throws Exception
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
        String request="POST / HTTP/1.1\r\n"+
                       "Host: whatever\r\n"+
                       "Content-Type: "+MimeTypes.Type.FORM_ENCODED.asString()+"\n"+
                       "Connection: close\n"+
                       "Content-Length: 100\n"+
                       "\n"+
                       "name=value";

        LocalEndPoint endp = _connector.connect();
        endp.addInput(request);

        String response = BufferUtil.toString(endp.waitForResponse(false, 1, TimeUnit.SECONDS));
        assertThat("Responses", response, startsWith("HTTP/1.1 500"));
    }
