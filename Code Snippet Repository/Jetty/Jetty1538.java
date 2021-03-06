    @Test
    public void testGetRequestBuffer11()
    {
        HttpTester.Request request =HttpTester.parseRequest(
            "GET /uri HTTP/1.1\r\n"+
            "Host: localhost\r\n"+
            "\r\n"+
            "GET /some/other/request /HTTP/1.1\r\n"+
            "Host: localhost\r\n"+
            "\r\n"
        );
        assertThat(request.getMethod(),is("GET"));
        assertThat(request.getUri(),is("/uri"));
        assertThat(request.getVersion(),is(HttpVersion.HTTP_1_1));
        assertThat(request.get(HttpHeader.HOST),is("localhost"));
        assertThat(request.getContent(),is(""));
    }
