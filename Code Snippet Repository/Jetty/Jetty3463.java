    @Test
    public void testWriteHugeKnown() throws Exception
    {
        _handler._writeLengthIfKnown=true;
        _handler._content=BufferUtil.allocate(4*1024*1024);
        _handler._content.limit(_handler._content.capacity());
        for (int i=_handler._content.capacity();i-->0;)
            _handler._content.put(i,(byte)'x');
        _handler._arrayBuffer=new byte[8192];
        
        String response=_connector.getResponse("GET / HTTP/1.0\nHost: localhost:80\n\n");
        assertThat(response,containsString("HTTP/1.1 200 OK"));
        assertThat(response,containsString("Content-Length"));
    }
