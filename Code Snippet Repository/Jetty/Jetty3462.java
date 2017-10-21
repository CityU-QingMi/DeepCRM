    @Test
    public void testWriteLargeKnown() throws Exception
    {
        final Resource big = Resource.newClassPathResource("simple/big.txt");
        _handler._writeLengthIfKnown=true;
        _handler._content=BufferUtil.toBuffer(big,false);
        _handler._arrayBuffer=new byte[8192];
        
        String response=_connector.getResponse("GET / HTTP/1.0\nHost: localhost:80\n\n");
        assertThat(response,containsString("HTTP/1.1 200 OK"));
        assertThat(response,containsString("Content-Length"));
        assertThat(response, endsWith(toUTF8String(big)));
    }
