    @Test
    public void testInitialEarlyEOFListenInHandle() throws Exception
    {
        deliver(EARLY_EOF_CONTENT);
        check();      
        
        handle(()->
        {
            _state.startAsync(null);
            _in.setReadListener(_listener);
            check("onReadReady false");       
        });

        check("onError:org.eclipse.jetty.io.EofException: Early EOF");       
    }
