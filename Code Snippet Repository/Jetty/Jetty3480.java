    @Test
    public void testAsyncWriteSimpleKnownHEAD() throws Exception
    {
        final Resource big = Resource.newClassPathResource("simple/simple.txt");
        
        _handler._async=true;
        _handler._writeLengthIfKnown=true;
        _handler._content=BufferUtil.toBuffer(big,false);
        _handler._arrayBuffer=new byte[4000];

        int start=_handler._owp.get();
        String response=_connector.getResponse("HEAD / HTTP/1.0\nHost: localhost:80\n\n");
        assertThat(_handler._owp.get()-start,Matchers.equalTo(1));
        assertThat(response,containsString("HTTP/1.1 200 OK"));
        assertThat(response,containsString("Content-Length: 11"));
        assertThat(response,Matchers.not(containsString("simple text")));
    }
