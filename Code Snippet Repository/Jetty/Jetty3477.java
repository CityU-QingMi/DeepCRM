    @Test
    public void testAsyncWriteBufferLargeHEAD() throws Exception
    {
        final Resource big = Resource.newClassPathResource("simple/big.txt");
        _handler._writeLengthIfKnown=false;
        _handler._content=BufferUtil.toBuffer(big,false);
        _handler._byteBuffer=BufferUtil.allocate(8192);
        _handler._async=true;
        
        int start=_handler._owp.get();
        String response=_connector.getResponse("HEAD / HTTP/1.0\nHost: localhost:80\n\n");
        assertThat(_handler._owp.get()-start,Matchers.greaterThan(0));
        assertThat(response,containsString("HTTP/1.1 200 OK"));
        assertThat(response,Matchers.not(containsString("Content-Length")));
        assertThat(response, Matchers.not(containsString("1\tThis is a big file")));
        assertThat(response, Matchers.not(containsString("400\tThis is a big file")));
    }
