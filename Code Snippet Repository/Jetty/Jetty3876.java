    public void setGzipHandler(GzipHandler gzipHandler)
    {
        if (isStarted())
            throw new IllegalStateException("STARTED");

        Handler next=null;
        if (_gzipHandler!=null)
        {
            next=_gzipHandler.getHandler();
            _gzipHandler.setHandler(null);
            replaceHandler(_gzipHandler,gzipHandler);
        }
        
        _gzipHandler = gzipHandler;
        if (next!=null && _gzipHandler.getHandler()==null)
            _gzipHandler.setHandler(next);
        relinkHandlers();
    }
