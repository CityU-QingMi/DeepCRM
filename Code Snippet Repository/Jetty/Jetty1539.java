    @Test
    public void testPostRequestBuffer10()
    {
        HttpTester.Request request =HttpTester.parseRequest(
            "POST /uri HTTP/1.0\r\n"+
            "Host: localhost\r\n"+
            "Connection: keep-alive\r\n"+
            "Content-Length: 16\r\n"+
            "\r\n"+
            "0123456789ABCDEF"+
            "\r\n"+
            "GET /some/other/request /HTTP/1.0\r\n"+
            "Host: localhost\r\n"+
            "\r\n"
        );
        assertThat(request.getMethod(),is("POST"));
        assertThat(request.getUri(),is("/uri"));
        assertThat(request.getVersion(),is(HttpVersion.HTTP_1_0));
        assertThat(request.get(HttpHeader.HOST),is("localhost"));
        assertThat(request.getContent(),is("0123456789ABCDEF"));
        
    }
