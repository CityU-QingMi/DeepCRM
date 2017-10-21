    @Test
    public void testChunk() throws Exception
    {
        String response=connector.getResponse("GET /R1 HTTP/1.1\r\n"+
                "Host: localhost\r\n"+
                "Transfer-Encoding: chunked\r\n"+
                "Content-Type: text/plain\r\n"+
                "Connection: close\r\n"+
                "\r\n"+
                "A\r\n" +
                "0123456789\r\n"+
                "0\r\n" +
                "\r\n");

        int offset=0;
        offset = checkContains(response,offset,"HTTP/1.1 200");
        offset = checkContains(response,offset,"/R1");
        checkContains(response,offset,"0123456789");
    }
