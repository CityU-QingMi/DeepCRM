    @Test
    public void testResponseLengthBuffer()
    {
        HttpTester.Response response =HttpTester.parseResponse(
            "HTTP/1.1 200 OK\r\n"+
            "Header: value\r\n"+
            "Content-Length: 16\r\n"+
            "\r\n"+
            "0123456789ABCDEF"+
            "HTTP/1.1 200 OK\r\n"+
            "\r\n"
        );

        assertThat(response.getVersion(),is(HttpVersion.HTTP_1_1));
        assertThat(response.getStatus(),is(200));
        assertThat(response.getReason(),is("OK"));
        assertThat(response.get("Header"),is("value"));
        assertThat(response.getContent(),is("0123456789ABCDEF")); 
    }
