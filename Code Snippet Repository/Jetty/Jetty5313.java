    @Override
    public void setMinThreads(int minThreads)
    {
        _minThreads = minThreads;

        if (_minThreads > _maxThreads)
            _maxThreads = _minThreads;

        int threads = _threadsStarted.get();
        if (isStarted() && threads < _minThreads)
            startThreads(_minThreads - threads);
    }
