    public void onCompleted()
    {
        if (LOG.isDebugEnabled())
            LOG.debug("COMPLETE for {} written={}",getRequest().getRequestURI(),getBytesWritten());

        if (_requestLog!=null )
            _requestLog.log(_request, _response);

        long idleTO=_configuration.getIdleTimeout();
        if (idleTO>=0 && getIdleTimeout()!=_oldIdleTimeout)
            setIdleTimeout(_oldIdleTimeout);

        _transport.onCompleted();
    }
