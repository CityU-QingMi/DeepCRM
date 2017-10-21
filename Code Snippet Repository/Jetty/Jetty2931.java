    @Override
    public AsyncContext startAsync() throws IllegalStateException
    {
        if (_asyncNotSupportedSource!=null)
            throw new IllegalStateException("!asyncSupported: "+_asyncNotSupportedSource);
        HttpChannelState state = getHttpChannelState();
        if (_async==null)
            _async=new AsyncContextState(state);
        AsyncContextEvent event = new AsyncContextEvent(_context,_async,state,this,this,getResponse());
        state.startAsync(event);
        return _async;
    }
