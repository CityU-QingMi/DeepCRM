    @Test
    public void testInitialAllContentListenInHandle() throws Exception
    {
        deliver(new TContent("Hello"),EOF_CONTENT);
        check();      
        
        handle(()->
        {
            _state.startAsync(null);
            _in.setReadListener(_listener);
            check("onReadReady false");       
        });

        check("onDataAvailable","read 5","read -1","onAllDataRead");       
    }
