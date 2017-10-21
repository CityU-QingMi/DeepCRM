    @Test
    public void testListenAfterHandleEarlyEOF() throws Exception
    {
        handle(()->
        {
            _state.startAsync(null);
            check();       
        });

        _in.setReadListener(_listener);
        check("onAsyncWaitForContent","onReadUnready");

        deliver(EARLY_EOF_CONTENT);
        check("onReadPossible true");        
        
        handle();
        check("onError:org.eclipse.jetty.io.EofException: Early EOF");      
    }
