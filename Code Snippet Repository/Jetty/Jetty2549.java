    protected void reset()
    {
        if (_latch!=null)
            _latch.countDown();
        _request = null;
        _response = null;
        _latch=new CountDownLatch(1);
        _connector.executeRequest("GET / HTTP/1.0\nCookie: set=already\n\n");
        
        while (_response==null)
            Thread.yield();
    }
