    @Test
    public void testListenAfterHandleAllContent() throws Exception
    {
        handle(()->
        {
            _state.startAsync(null);
            check();       
        });

        _in.setReadListener(_listener);
        check("onAsyncWaitForContent","onReadUnready");

        deliver(new TContent("Hello"),EOF_CONTENT);
        check("onReadPossible true","onReadPossible false");        
        
        handle();
        check("onDataAvailable","read 5","read -1","onAllDataRead");
    }
