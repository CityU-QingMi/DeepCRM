    @Test
    public void testListenInHandleEarlyEOF() throws Exception
    {
        handle(()->
        {
            _state.startAsync(null);
            _in.setReadListener(_listener);
            check("onReadUnready");       
        });

        check("onAsyncWaitForContent");
        
        deliver(EARLY_EOF_CONTENT);
        check("onReadPossible true");
        handle();
        check("onError:org.eclipse.jetty.io.EofException: Early EOF");      
    }
