    @Override
    public C get() throws InterruptedException, ExecutionException
    {
        _latch.await();
        if (_cause==COMPLETED)
            return _result;
        if (_cause instanceof CancellationException)
            throw (CancellationException) new CancellationException().initCause(_cause);
        throw new ExecutionException(_cause);
    }
