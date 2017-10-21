    @Test
    public void testWriteInterception() throws Exception
    {
        final Resource big = Resource.newClassPathResource("simple/big.txt");
        _handler._writeLengthIfKnown=false;
        _handler._content=BufferUtil.toBuffer(big,false);
        _handler._arrayBuffer=new byte[1024];
        _handler._interceptor = new ChainedInterceptor()
        {
            Interceptor _next;
            @Override
            public void write(ByteBuffer content, boolean complete, Callback callback)
            {
                String s = BufferUtil.toString(content).toUpperCase().replaceAll("BIG","BIGGER");
                _next.write(BufferUtil.toBuffer(s),complete,callback);
            }
            
            @Override
            public boolean isOptimizedForDirectBuffers()
            {
                return _next.isOptimizedForDirectBuffers();
            }
            
            @Override
            public Interceptor getNextInterceptor()
            {
                return _next;
            }
            
            @Override
            public void setNext(Interceptor interceptor)
            {
                _next=interceptor;
            }
        };
           
        String response=_connector.getResponse("GET / HTTP/1.0\nHost: localhost:80\n\n");
        assertThat(response,containsString("HTTP/1.1 200 OK"));
        assertThat(response,Matchers.not(containsString("Content-Length")));
        assertThat(response,containsString("400\tTHIS IS A BIGGER FILE"));
    }
