    @Override
    public void setReadListener(ReadListener readListener)
    {
        readListener = Objects.requireNonNull(readListener);
        boolean woken = false;
        try
        {
            synchronized (_inputQ)
            {
                if (_listener != null)
                    throw new IllegalStateException("ReadListener already set");
               
                _listener = readListener;
                
                Content content = produceNextContext();
                if (content!=null)
                {
                    _state = ASYNC;
                    woken = _channelState.onReadReady();
                }
                else if (_state == EOF)
                {
                    _state = AEOF;
                    woken = _channelState.onReadEof();
                }
                else 
                {
                    _state = ASYNC;
                    _channelState.onReadUnready();
                    _waitingForContent = true;
                }
            }
        }
        catch (IOException e)
        {
            throw new RuntimeIOException(e);
        }

        if (woken)
            wake();
    }
