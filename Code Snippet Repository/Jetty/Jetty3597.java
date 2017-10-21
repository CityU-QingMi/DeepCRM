    @Test
    public void test4_4_3() throws Exception
    {
        // _content length is ignored, as chunking is used. If it is
        // not ignored, the second request wont be seen.
        int offset=0;
        String response;
        LocalEndPoint endp=connector.executeRequest(
                "GET /R1 HTTP/1.1\n" +
                        "Host: localhost\n" +
                        "Transfer-Encoding: chunked\n" +
                        "Content-Type: text/plain\n" +
                        "Content-Length: 100\n" +
                        "\n" +
                        "3;\n" +
                        "123\n" +
                        "3;\n" +
                        "456\n" +
                        "0;\n" +
                        "\n" +

                        "GET /R2 HTTP/1.1\n" +
                        "Host: localhost\n" +
                        "Connection: close\n" +
                        "Content-Type: text/plain\n" +
                        "Content-Length: 6\n" +
                        "\n" +
                        "abcdef");
        offset=0;
        response = endp.getResponse();
        offset=checkContains(response,offset,"HTTP/1.1 200 OK","3. ignore c-l")+1;
        offset=checkContains(response,offset,"/R1","3. ignore c-l")+1;
        offset=checkContains(response,offset,"123456","3. ignore c-l")+1;
        offset=0;
        response = endp.getResponse();
        offset=checkContains(response,offset,"HTTP/1.1 200 OK","3. ignore c-l")+1;
        offset=checkContains(response,offset,"/R2","3. _content-length")+1;
        offset=checkContains(response,offset,"abcdef","3. _content-length")+1;
    }
