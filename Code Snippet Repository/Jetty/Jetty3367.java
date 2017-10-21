    @Test
    public void testPartialContentListenAfterHandle() throws Exception
    {
        handle(()->
        {
            _state.startAsync(null);
            check();       
        });

        deliver(new TContent("Hello"),EARLY_EOF_CONTENT);
        check();       
        
        _in.setReadListener(_listener);
        check("onReadReady true","wake");
        wake();
        check(
            "onDataAvailable",
            "read 5",
            "read org.eclipse.jetty.io.EofException: Early EOF",
            "onError:org.eclipse.jetty.io.EofException: Early EOF");  
    }
