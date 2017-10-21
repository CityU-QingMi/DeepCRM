    @Override
    public void clear()
    {

        _tailLock.lock();
        try
        {

            _headLock.lock();
            try
            {
                _indexes[HEAD_OFFSET] = 0;
                _indexes[TAIL_OFFSET] = 0;
                _size.set(0);
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
