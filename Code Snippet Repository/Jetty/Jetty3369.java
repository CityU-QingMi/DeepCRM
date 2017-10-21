    @Test
    public void testReadAfterOnDataAvailable() throws Exception
    {
        _noReadInDataAvailable = true;
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
        check("onDataAvailable"); 
        
        readAvailable();
        check("wake","read 5","read -1"); 
        wake();
        check("onAllDataRead");   
    }
