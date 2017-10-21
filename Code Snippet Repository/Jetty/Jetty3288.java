    @Test
    public void test404HtmlAcceptNotUtf8UnknownCharset() throws Exception
    {
        String response = connector.getResponse(
            "GET / HTTP/1.1\r\n"+
            "Host: Localhost\r\n"+
            "Accept: text/html\r\n"+
            "Accept-Charset: utf-8;q=0,unknown\r\n"+
            "\r\n");

        assertThat(response,startsWith("HTTP/1.1 404 "));
        assertThat(response,containsString("Content-Length: 0"));
        assertThat(response,not(containsString("Content-Type")));
    }
