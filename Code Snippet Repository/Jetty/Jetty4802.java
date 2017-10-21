    @Override
    public int remainingCapacity()
    {

        _tailLock.lock();
        try
        {

            _headLock.lock();
            try
            {
                return getCapacity() - size();
            }
            finally
            {
                _headLock.unlock();
            }
        }
        finally
        {
            _tailLock.unlock();
        }
    }
