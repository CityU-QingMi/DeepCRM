    @Test
    public void testResponseEOFBuffer()
    {
        HttpTester.Response response =HttpTester.parseResponse(
            "HTTP/1.1 200 OK\r\n"+
            "Header: value\r\n"+
            "Connection: close\r\n"+
            "\r\n"+
            "0123456789ABCDEF"
        );

        assertThat(response.getVersion(),is(HttpVersion.HTTP_1_1));
        assertThat(response.getStatus(),is(200));
        assertThat(response.getReason(),is("OK"));
        assertThat(response.get("Header"),is("value"));
        assertThat(response.getContent(),is("0123456789ABCDEF")); 
    }
