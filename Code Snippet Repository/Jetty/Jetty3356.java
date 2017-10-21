    @Test
    public void testEarlyEOFListenAfterHandle() throws Exception
    {
        handle(()->
        {
            _state.startAsync(null);
            check();       
        });

        deliver(EARLY_EOF_CONTENT);
        check();       
        
        _in.setReadListener(_listener);
        check("onReadReady true","wake");
        wake();
        check("onError:org.eclipse.jetty.io.EofException: Early EOF");       
    }
