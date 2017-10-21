    @Test
    public void testAllContentListenAfterHandle() throws Exception
    {
        handle(()->
        {
            _state.startAsync(null);
            check();       
        });

        deliver(new TContent("Hello"),EOF_CONTENT);
        check();       
        
        _in.setReadListener(_listener);
        check("onReadReady true","wake");
        wake();
        check("onDataAvailable","read 5","read -1","onAllDataRead");
    }
