    @Test
    public void test3_6_a() throws Exception
    {
        int offset=0;
        // Chunk last
        String response = connector.getResponse(
                "GET /R1 HTTP/1.1\n" +
                        "Host: localhost\n" +
                        "Transfer-Encoding: chunked,identity\n" +
                        "Content-Type: text/plain\n" +
                        "\015\012" +
                        "5;\015\012" +
                        "123\015\012\015\012" +
                        "0;\015\012\015\012");
        checkContains(response,offset,"HTTP/1.1 400 Bad","Chunked last");
    }
