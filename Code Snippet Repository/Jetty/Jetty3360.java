    @Test
    public void testListenInHandleAllContent() throws Exception
    {
        handle(()->
        {
            _state.startAsync(null);
            _in.setReadListener(_listener);
            check("onReadUnready");       
        });

        check("onAsyncWaitForContent");
        
        deliver(new TContent("Hello"),EOF_CONTENT);
        check("onReadPossible true","onReadPossible false");
        handle();
        check("onDataAvailable","read 5","read -1","onAllDataRead");
    }
