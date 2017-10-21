    public Blocker acquire() throws IOException
    {
        long idle = getIdleTimeout();
        _lock.lock();
        try
        {
            while (_blocker._state != IDLE)
            {
                if (idle>0 && (idle < Long.MAX_VALUE/2))
                {
                    // Wait a little bit longer than the blocker might block
                    if (!_idle.await(idle*2,TimeUnit.MILLISECONDS))
                        throw new IOException(new TimeoutException());
                }
                else
                    _idle.await();
            }
            _blocker._state = null;
            return _blocker;
        }
        catch (InterruptedException x)
        {
            throw new InterruptedIOException();
        }
        finally
        {
            _lock.unlock();
        }
    }
