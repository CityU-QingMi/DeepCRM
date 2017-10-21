    public void recycle()
    {
        _interceptor = _channel;
        HttpConfiguration config = _channel.getHttpConfiguration();
        _bufferSize = config.getOutputBufferSize();
        _commitSize = config.getOutputAggregationSize();
        if (_commitSize > _bufferSize)
            _commitSize = _bufferSize;
        releaseBuffer();
        _written = 0;
        _writeListener = null;
        _onError = null;
        reopen();
    }
