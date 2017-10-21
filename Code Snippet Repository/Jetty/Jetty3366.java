    @Test
    public void testListenInHandlePartialContent() throws Exception
    {
        handle(()->
        {
            _state.startAsync(null);
            _in.setReadListener(_listener);
            check("onReadUnready");       
        });

        check("onAsyncWaitForContent");
        
        deliver(new TContent("Hello"),EARLY_EOF_CONTENT);
        check("onReadPossible true","onReadPossible false");
        handle();
        check(
            "onDataAvailable",
            "read 5",
            "read org.eclipse.jetty.io.EofException: Early EOF",
            "onError:org.eclipse.jetty.io.EofException: Early EOF");  
    }
