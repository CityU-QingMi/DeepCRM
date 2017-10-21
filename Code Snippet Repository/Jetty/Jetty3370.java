    @Test
    public void testReadOnlyExpectedAfterOnDataAvailable() throws Exception
    {
        _noReadInDataAvailable = true;
        handle(()->
        {
            _state.startAsync(null);
            _in.setReadListener(_listener);
            check("onReadUnready");       
        });

        check("onAsyncWaitForContent");
        
        deliver(new TContent("Hello"),EOF_CONTENT);
        check("onReadPossible true","onReadPossible false");
        
        handle();
        check("onDataAvailable"); 
        
        byte[] buffer = new byte[_expected.remaining()];
        assertThat(_in.read(buffer),equalTo(buffer.length));
        assertThat(new String(buffer),equalTo(BufferUtil.toString(_expected)));
        BufferUtil.clear(_expected);
        check();
        
        assertTrue(_in.isReady());
        check();
        
        assertThat(_in.read(),equalTo(-1));
        check("wake"); 
        
        wake();
        check("onAllDataRead");   
    }
