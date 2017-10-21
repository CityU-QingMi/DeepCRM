    @Test
    public void testInitialPartialContentListenAfterHandle() throws Exception
    {
        deliver(new TContent("Hello"),EARLY_EOF_CONTENT);
        
        handle(()->
        {
            _state.startAsync(null);
            check();       
        });

        _in.setReadListener(_listener);
        check("onReadReady true","wake");
        wake();
        check(
            "onDataAvailable",
            "read 5",
            "read org.eclipse.jetty.io.EofException: Early EOF",
            "onError:org.eclipse.jetty.io.EofException: Early EOF");  
    }
