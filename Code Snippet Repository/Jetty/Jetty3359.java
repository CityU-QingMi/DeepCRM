    @Test
    public void testInitialAllContentListenAfterHandle() throws Exception
    {
        deliver(new TContent("Hello"),EOF_CONTENT);
        
        handle(()->
        {
            _state.startAsync(null);
            check();       
        });

        _in.setReadListener(_listener);
        check("onReadReady true","wake");
        wake();
        check("onDataAvailable","read 5","read -1","onAllDataRead"); 
    }
