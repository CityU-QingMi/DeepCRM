    @Test
    public void test404HtmlAcceptNotUtf8Charset() throws Exception
    {
        String response = connector.getResponse(
            "GET / HTTP/1.1\r\n"+
            "Host: Localhost\r\n"+
            "Accept: text/html\r\n"+
            "Accept-Charset: utf-8;q=0\r\n"+
            "\r\n");
        
        assertThat(response,startsWith("HTTP/1.1 404 "));
        assertThat(response,not(containsString("Content-Length: 0")));
        assertThat(response,containsString("Content-Type: text/html;charset=iso-8859-1"));
    }
