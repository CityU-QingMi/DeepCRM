    @Test
    public void testAutoFlush() throws Exception
    {
        int offset=0;

        String response = connector.getResponse("GET /R1 HTTP/1.1\r\n" +
                "Host: localhost\r\n" +
                "Transfer-Encoding: chunked\r\n" +
                "Content-Type: text/plain\r\n" +
                "Connection: close\r\n" +
                "\r\n" +
                "5;\r\n" +
                "12345\r\n" +
                "0;\r\n" +
                "\r\n");
        offset = checkContains(response,offset,"HTTP/1.1 200");
        checkNotContained(response,offset,"IgnoreMe");
        offset = checkContains(response,offset,"/R1");
        checkContains(response,offset,"12345");
    }
