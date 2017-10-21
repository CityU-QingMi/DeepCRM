    @Test
    public void testResponsesInput() throws Exception
    {
        ByteArrayInputStream stream = new ByteArrayInputStream((
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
            "HTTP/1.1 400 OK\r\n"+
            "Next: response\r\n"+
            "Content-Length: 16\r\n"+
            "\r\n"+
            "0123456789ABCDEF").getBytes(StandardCharsets.ISO_8859_1)
        );

        HttpTester.Input in = HttpTester.from(stream);
        
        HttpTester.Response response = HttpTester.parseResponse(in);
        
        assertThat(response.getVersion(),is(HttpVersion.HTTP_1_1));
        assertThat(response.getStatus(),is(200));
        assertThat(response.getReason(),is("OK"));
        assertThat(response.get("Header"),is("value"));
        assertThat(response.getContent(),is("0123456789ABCDEF")); 
        
        response = HttpTester.parseResponse(in);
        assertThat(response.getVersion(),is(HttpVersion.HTTP_1_1));
        assertThat(response.getStatus(),is(400));
        assertThat(response.getReason(),is("OK"));
        assertThat(response.get("Next"),is("response"));
        assertThat(response.getContent(),is("0123456789ABCDEF"));    
    }
