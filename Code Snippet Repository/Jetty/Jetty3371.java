    @Test
    public void testReadAndCompleteInOnDataAvailable() throws Exception
    {
        _completeInOnDataAvailable = true;
        handle(()->
        {
            _state.startAsync(null);
            _in.setReadListener(_listener);
            check("onReadUnready");       
        });

        check("onAsyncWaitForContent");
        
        deliver(new TContent("Hello"),EOF_CONTENT);
        check("onReadPossible true","onReadPossible false");
        
        handle(()->{__history.add(_state.getState().toString());});
        System.err.println(__history);
        check(
            "onDataAvailable",
            "read 5",
            "read -1",
            "complete",
            "COMPLETE"
            ); 
    }
