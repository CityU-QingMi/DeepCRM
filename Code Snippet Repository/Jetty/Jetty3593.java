    @Test
    public void test3_6_c() throws Exception
    {
        String response;
        int offset=0;
        LocalEndPoint endp=connector.executeRequest(
                "POST /R1 HTTP/1.1\n" +
                        "Host: localhost\n" +
                        "Transfer-Encoding: chunked\n" +
                        "Content-Type: text/plain\n" +
                        "\n" +
                        "3;\n" +
                        "fgh\n" +
                        "3;\n" +
                        "Ijk\n" +
                        "0;\n\n" +

                        "POST /R2 HTTP/1.1\n" +
                        "Host: localhost\n" +
                        "Transfer-Encoding: chunked\n" +
                        "Content-Type: text/plain\n" +
                        "\n" +
                        "4;\n" +
                        "lmno\n" +
                        "5;\n" +
                        "Pqrst\n" +
                        "0;\n\n" +

                        "GET /R3 HTTP/1.1\n" +
                        "Host: localhost\n" +
                        "Connection: close\n" +
                        "\n");
        offset=0;
        response = endp.getResponse();
        checkNotContained(response,"HTTP/1.1 100","3.6.1 Chunking");
        offset=checkContains(response,offset,"HTTP/1.1 200","3.6.1 Chunking");
        offset=checkContains(response,offset,"fghIjk","3.6.1 Chunking");
        offset=0;
        response = endp.getResponse();
        checkNotContained(response,"HTTP/1.1 100","3.6.1 Chunking");
        offset=checkContains(response,offset,"HTTP/1.1 200","3.6.1 Chunking");
        offset=checkContains(response,offset,"lmnoPqrst","3.6.1 Chunking");
        offset=0;
        response = endp.getResponse();
        checkNotContained(response,"HTTP/1.1 100","3.6.1 Chunking");
        offset=checkContains(response,offset,"/R3","3.6.1 Chunking");
    }
