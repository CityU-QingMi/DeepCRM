    @Test
    public void testEmptyChunk() throws Exception
    {
        String response=connector.getResponse("GET /R1 HTTP/1.1\r\n"+
                "Host: localhost\r\n"+
                "Transfer-Encoding: chunked\r\n"+
                "Content-Type: text/plain\r\n"+
                "Connection: close\r\n"+
                "\r\n"+
                "0\r\n" +
                "\r\n");

        int offset=0;
        offset = checkContains(response,offset,"HTTP/1.1 200");
        checkContains(response,offset,"/R1");
    }
