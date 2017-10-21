    public void recycle()
    {
        synchronized (_inputQ)
        {
            if (_content!=null)
                _content.failed(null);
            _content = null;
            Content item = _inputQ.poll();
            while (item != null)
            {
                item.failed(null);
                item = _inputQ.poll();
            }
            _listener = null;
            _state = STREAM;
            _contentArrived = 0;
            _contentConsumed = 0;
            _firstByteTimeStamp = -1;
            _blockUntil = 0;
            _waitingForContent = false;
            if (_interceptor instanceof Destroyable)
                ((Destroyable)_interceptor).destroy();
            _interceptor = null;
        }
    }
