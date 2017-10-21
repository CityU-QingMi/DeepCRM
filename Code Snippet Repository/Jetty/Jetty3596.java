    @Test
    public void test4_4_2() throws Exception
    {
        String response;
        int offset=0;
        // If _content length not used, second request will not be read.
        LocalEndPoint endp=connector.executeRequest(
                "GET /R1 HTTP/1.1\n" +
                        "Host: localhost\n" +
                        "Transfer-Encoding: identity\n" +
                        "Content-Type: text/plain\n" +
                        "Content-Length: 5\n" +
                        "\n" +
                        "123\015\012" +

                        "GET /R2 HTTP/1.1\n" +
                        "Host: localhost\n" +
                        "Transfer-Encoding: other\n" +
                        "Connection: close\n" +
                        "\n");
        offset=0;
        response = endp.getResponse();
        offset=checkContains(response,offset,"HTTP/1.1 200 OK","2. identity")+10;
        offset=checkContains(response,offset,"/R1","2. identity")+3;
        offset=0;
        response = endp.getResponse();
        offset=checkContains(response,offset,"HTTP/1.1 200 OK","2. identity")+10;
        offset=checkContains(response,offset,"/R2","2. identity")+3;
    }
