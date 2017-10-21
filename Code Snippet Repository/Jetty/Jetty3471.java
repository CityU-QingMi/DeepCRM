    @Test
    public void testAsyncWriteLarge() throws Exception
    {
        final Resource big = Resource.newClassPathResource("simple/big.txt");
        _handler._writeLengthIfKnown=false;
        _handler._content=BufferUtil.toBuffer(big,false);
        _handler._arrayBuffer=new byte[8192];
        _handler._async=true;
        
        String response=_connector.getResponse("GET / HTTP/1.0\nHost: localhost:80\n\n");
        assertThat(response,containsString("HTTP/1.1 200 OK"));
        assertThat(response,Matchers.not(containsString("Content-Length")));
        assertThat(response, endsWith(toUTF8String(big)));
    }
