    @Test
    public void test3_6_b() throws Exception
    {
        String response;
        int offset=0;
        // Chunked
        LocalEndPoint endp=connector.executeRequest(
                "GET /R1 HTTP/1.1\n" +
                        "Host: localhost\n" +
                        "Transfer-Encoding: chunked\n" +
                        "Content-Type: text/plain\n" +
                        "\n" +
                        "2;\n" +
                        "12\n" +
                        "3;\n" +
                        "345\n" +
                        "0;\n\n" +

                        "GET /R2 HTTP/1.1\n" +
                        "Host: localhost\n" +
                        "Transfer-Encoding: chunked\n" +
                        "Content-Type: text/plain\n" +
                        "\n" +
                        "4;\n" +
                        "6789\n" +
                        "5;\n" +
                        "abcde\n" +
                        "0;\n\n" +

                        "GET /R3 HTTP/1.1\n" +
                        "Host: localhost\n" +
                        "Connection: close\n" +
                        "\n");
        offset=0;
        response = endp.getResponse();
        offset=checkContains(response,offset,"HTTP/1.1 200","3.6.1 Chunking");
        offset=checkContains(response,offset,"12345","3.6.1 Chunking");
        offset=0;
        response = endp.getResponse();
        offset=checkContains(response,offset,"HTTP/1.1 200","3.6.1 Chunking");
        offset=checkContains(response,offset,"6789abcde","3.6.1 Chunking");
        offset=0;
        response = endp.getResponse();
        offset=checkContains(response,offset,"/R3","3.6.1 Chunking");
    }
