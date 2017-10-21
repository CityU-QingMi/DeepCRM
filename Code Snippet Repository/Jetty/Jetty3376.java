    @Test
    public void testListenInHandleEmpty() throws Exception
    {
        handle(()->
        {
            _state.startAsync(null);
            _in.setReadListener(_listener);
            check("onReadUnready");       
        });

        check("onAsyncWaitForContent");
        
        deliver(EOF_CONTENT);
        check("onReadPossible true");
        handle();
        check("onAllDataRead");       
    }
