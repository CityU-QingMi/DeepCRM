    @Test
    public void testListenAfterHandleEmpty() throws Exception
    {
        handle(()->
        {
            _state.startAsync(null);
            check();       
        });

        _in.setReadListener(_listener);
        check("onAsyncWaitForContent","onReadUnready");

        deliver(EOF_CONTENT);
        check("onReadPossible true");        
        
        handle();
        check("onAllDataRead");       
    }
