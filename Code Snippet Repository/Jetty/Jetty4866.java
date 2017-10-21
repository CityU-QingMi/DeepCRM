    @Override
    public void succeeded(C result)
    {
        if (_done.compareAndSet(false,true))
        {
            _result=result;
            _cause=COMPLETED;
            _latch.countDown();
        }
    }
