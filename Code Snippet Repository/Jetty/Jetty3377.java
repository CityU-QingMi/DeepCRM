    @Test
    public void testEmptyListenAfterHandle() throws Exception
    {
        handle(()->
        {
            _state.startAsync(null);
            check();       
        });

        deliver(EOF_CONTENT);
        check();       
        
        _in.setReadListener(_listener);
        check("onReadReady true","wake");
        wake();
        check("onAllDataRead");       
    }
