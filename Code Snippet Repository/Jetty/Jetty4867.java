    @Override
    public boolean cancel(boolean mayInterruptIfRunning)
    {
        if (_done.compareAndSet(false,true))
        {
            _result=null;
            _cause=new CancellationException();
            _latch.countDown();
            return true;
        }
        return false;
    }
