    @Test
    public void testSendInputStreamBigChunked() throws Exception
    {
        Resource big = Resource.newClassPathResource("simple/big.txt");
        _handler._contentInputStream= new FilterInputStream(big.getInputStream())
        {
            @Override
            public int read(byte[] b, int off, int len) throws IOException
            {
                int filled= super.read(b,off,len>2000?2000:len);
                return filled;
            }
        };
        LocalEndPoint endp=_connector.executeRequest(
            "GET / HTTP/1.1\nHost: localhost:80\n\n"+
            "GET / HTTP/1.1\nHost: localhost:80\nConnection: close\n\n"
        );
        
        String response = endp.getResponse();
                
        assertThat(response,containsString("HTTP/1.1 200 OK"));
        assertThat(response,containsString("Transfer-Encoding: chunked"));
        assertThat(response, containsString("1\tThis is a big file"));
        assertThat(response,containsString("400\tThis is a big file"));
        assertThat(response,containsString("\r\n0\r\n"));
        
        response = endp.getResponse();
        assertThat(response,containsString("HTTP/1.1 200 OK"));
        assertThat(response,containsString("Connection: close"));

    }
