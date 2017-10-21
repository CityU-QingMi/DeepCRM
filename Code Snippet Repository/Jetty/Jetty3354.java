    @Test
    public void testInitialEarlyEOFListenAfterHandle() throws Exception
    {
        deliver(EARLY_EOF_CONTENT);
        
        handle(()->
        {
            _state.startAsync(null);
            check();       
        });

        _in.setReadListener(_listener);
        check("onReadReady true","wake");
        wake();
        check("onError:org.eclipse.jetty.io.EofException: Early EOF");        
    }
