    @Test
    public void testInitialEmptyListenInHandle() throws Exception
    {
        deliver(EOF_CONTENT);
        check();      
        
        handle(()->
        {
            _state.startAsync(null);
            _in.setReadListener(_listener);
            check("onReadReady false");       
        });

        check("onAllDataRead");       
    }
