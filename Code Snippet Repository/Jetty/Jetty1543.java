    @Test
    public void testResponseChunkedBuffer()
    {
        HttpTester.Response response =HttpTester.parseResponse(
            "HTTP/1.1 200 OK\r\n"+
            "Header: value\r\n"+
            "Transfer-Encoding: chunked\r\n"+
            "\r\n"+
            "A\r\n"+
            "0123456789\r\n"+
            "6\r\n"+
            "ABCDEF\r\n"+
            "0\r\n"+
            "\r\n"+
            "HTTP/1.1 200 OK\r\n"+
            "\r\n"
        );

        assertThat(response.getVersion(),is(HttpVersion.HTTP_1_1));
        assertThat(response.getStatus(),is(200));
        assertThat(response.getReason(),is("OK"));
        assertThat(response.get("Header"),is("value"));
        assertThat(response.getContent(),is("0123456789ABCDEF")); 
    }
