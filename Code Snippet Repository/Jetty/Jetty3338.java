    @Test
    public void testUnconsumedErrorStream() throws Exception
    {
        int offset=0;
        String requests=
        "GET /R1?error=599 HTTP/1.1\r\n"+
        "Host: localhost\r\n"+
        "Transfer-Encoding: chunked\r\n"+
        "Content-Type: application/data; charset=utf-8\r\n"+
        "\r\n"+
        "5;\r\n"+
        "12345\r\n"+
        "5;\r\n"+
        "67890\r\n"+
        "0;\r\n" +
        "\r\n"+
        "GET /R2 HTTP/1.1\r\n"+
        "Host: localhost\r\n"+
        "Content-Type: text/plain; charset=utf-8\r\n"+
        "Content-Length: 10\r\n"+
        "Connection: close\r\n"+
        "\r\n"+
        "abcdefghij\r\n";

        LocalEndPoint endp = connector.executeRequest(requests);
        String response = endp.getResponse()+endp.getResponse();

        offset = checkContains(response,offset,"HTTP/1.1 599");
        offset = checkContains(response,offset,"HTTP/1.1 200");
        offset = checkContains(response,offset,"/R2");
        offset = checkContains(response,offset,"encoding=UTF-8");
        checkContains(response,offset,"abcdefghij");
    }
