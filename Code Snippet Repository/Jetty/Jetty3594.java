    @Test
    public void test3_6_d() throws Exception
    {
        String response;
        int offset=0;
        // Chunked and keep alive
        LocalEndPoint endp=connector.executeRequest(
                "GET /R1 HTTP/1.1\n" +
                        "Host: localhost\n" +
                        "Transfer-Encoding: chunked\n" +
                        "Content-Type: text/plain\n" +
                        "Connection: keep-alive\n" +
                        "\n" +
                        "3;\n" +
                        "123\n" +
                        "3;\n" +
                        "456\n" +
                        "0;\n\n" +

                        "GET /R2 HTTP/1.1\n" +
                        "Host: localhost\n" +
                        "Connection: close\n" +
                        "\n");
        offset=0;
        response = endp.getResponse();
        offset=checkContains(response,offset,"HTTP/1.1 200","3.6.1 Chunking")+10;
        offset=checkContains(response,offset,"123456","3.6.1 Chunking");
        offset=0;
        response = endp.getResponse();
        offset=checkContains(response,offset,"/R2","3.6.1 Chunking")+10;
    }
