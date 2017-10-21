    public void reset()
    {
        _state = State.START;
        _endOfContent = EndOfContent.UNKNOWN_CONTENT;
        _noContentResponse=false;
        _persistent = null;
        _contentPrepared = 0;
        _needCRLF = false;
        _trailers = null;
    }
