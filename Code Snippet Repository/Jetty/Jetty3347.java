    @Test
    public void testSetDate() throws Exception
    {
        String response=connector.getResponse("GET /?date=1+Jan+1970 HTTP/1.1\r\n"+
                "Host: localhost:80\r\n"+
                "Connection: close\r\n"+
                "\r\n");

        int offset=0;
        offset = checkContains(response,offset,"HTTP/1.1 200");
        offset = checkContains(response,offset,"Date: 1 Jan 1970");
        checkContains(response,offset,"pathInfo=/");
    }
