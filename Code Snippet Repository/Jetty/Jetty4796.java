    public int getCapacity()
    {
        _tailLock.lock();
        try
        {
            return _elements.length;
        }
        finally
        {
            _tailLock.unlock();
        }
    }
