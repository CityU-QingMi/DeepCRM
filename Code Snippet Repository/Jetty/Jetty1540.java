    @Test
    public void testPostRequestBuffer11()
    {
        HttpTester.Request request =HttpTester.parseRequest(
            "POST /uri HTTP/1.1\r\n"+
            "Host: localhost\r\n"+
            "Transfer-Encoding: chunked\r\n"+
            "\r\n"+
            "A\r\n"+
            "0123456789\r\n"+
            "6\r\n"+
            "ABCDEF\r\n"+
            "0\r\n"+
            "\r\n"+
            "GET /some/other/request /HTTP/1.1\r\n"+
            "Host: localhost\r\n"+
            "\r\n"
        );
        assertThat(request.getMethod(),is("POST"));
        assertThat(request.getUri(),is("/uri"));
        assertThat(request.getVersion(),is(HttpVersion.HTTP_1_1));
        assertThat(request.get(HttpHeader.HOST),is("localhost"));
        assertThat(request.getContent(),is("0123456789ABCDEF"));
        
    }
