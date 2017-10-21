    @Override
    public boolean isCancelled()
    {
        if (_done.get())
        {
            try
            {
                _latch.await();
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
            return _cause instanceof CancellationException;
        }
        return false;
    }
