    @Test
    public void testInitialEmptyListenAfterHandle() throws Exception
    {
        deliver(EOF_CONTENT);
        
        handle(()->
        {
            _state.startAsync(null);
            check();       
        });

        _in.setReadListener(_listener);
        check("onReadReady true","wake");
        wake();
        check("onAllDataRead");       
    }
