    @Test
    public void testNoPath() throws Exception
    {
        String response=connector.getResponse("GET http://localhost:80 HTTP/1.1\r\n"+
                "Host: localhost:80\r\n"+
                "Connection: close\r\n"+
                "\r\n");

        int offset=0;
        offset = checkContains(response,offset,"HTTP/1.1 200");
        checkContains(response,offset,"pathInfo=/");
    }
