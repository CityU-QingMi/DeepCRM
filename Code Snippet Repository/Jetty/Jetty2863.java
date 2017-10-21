    @Override
    public void setWriteListener(WriteListener writeListener)
    {
        if (!_channel.getState().isAsync())
            throw new IllegalStateException("!ASYNC");

        if (_state.compareAndSet(OutputState.OPEN, OutputState.READY))
        {
            _writeListener = writeListener;
            if (_channel.getState().onWritePossible())
                _channel.execute(_channel);
        }
        else
            throw new IllegalStateException();
    }
