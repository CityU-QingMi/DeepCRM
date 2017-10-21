    public void reset()
    {
        if (DEBUG)
            LOG.debug("reset {}", this);

        // reset state
        if (_state==State.CLOSE || _state==State.CLOSED)
            return;

        setState(State.START);
        _endOfContent=EndOfContent.UNKNOWN_CONTENT;
        _contentLength=-1;
        _contentPosition=0;
        _responseStatus=0;
        _contentChunk=null;
        _headerBytes=0;
        _host=false;
        _headerComplete=false;
    }
