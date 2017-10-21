    public void recycle()
    {
        _committed.set(false);
        _request.recycle();
        _response.recycle();
        _committedMetaData=null;
        _requestLog=_connector==null?null:_connector.getServer().getRequestLog();
        _written=0;
        _trailers=null;
        _oldIdleTimeout=0;
    }
