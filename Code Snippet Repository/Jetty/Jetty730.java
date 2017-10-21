    @Override
    public void suspend(ServletResponse response)
    {
        _response=response;
        _responseWrapped=response instanceof ServletResponseWrapper;
        _resumed=false;
        _expired=false;
        _context=_request.startAsync();
        _context.setTimeout(_timeoutMs);
        _context.addListener(this);
    }
