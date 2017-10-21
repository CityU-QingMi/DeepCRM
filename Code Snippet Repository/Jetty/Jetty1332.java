    protected void badMessage(BadMessageException x)
    {
        if (DEBUG)
            LOG.debug("Parse exception: " + this + " for " + _handler, x);
        setState(State.CLOSE);
        if (_headerComplete)
            _handler.earlyEOF();
        else
            _handler.badMessage(x._code, x._reason);
    }
