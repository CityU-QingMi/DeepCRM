    @Test
    public void testInitialIncompleteContentListenInHandle() throws Exception
    {
        deliver(new TContent("Hello"),EARLY_EOF_CONTENT);
        check();      
        
        handle(()->
        {
            _state.startAsync(null);
            _in.setReadListener(_listener);
            check("onReadReady false");       
        });

        check(
            "onDataAvailable",
            "read 5",
            "read org.eclipse.jetty.io.EofException: Early EOF",
            "onError:org.eclipse.jetty.io.EofException: Early EOF");       
    }
