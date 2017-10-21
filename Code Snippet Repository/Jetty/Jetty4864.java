    @Override
    public Void get() throws InterruptedException, ExecutionException
    {
        _latch.await();
        if (_cause==COMPLETED)
            return null;
        if (_cause instanceof CancellationException)
            throw (CancellationException) new CancellationException().initCause(_cause);
        throw new ExecutionException(_cause);
    }
