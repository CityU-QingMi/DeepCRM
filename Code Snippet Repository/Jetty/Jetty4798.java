    @SuppressWarnings("")
    @Override
    public E peek()
    {
        if (_size.get() == 0)
            return null;

        E e = null;

        _headLock.lock(); // Size cannot shrink
        try
        {
            if (_size.get() > 0)
                e = (E)_elements[_indexes[HEAD_OFFSET]];
        }
        finally
        {
            _headLock.unlock();
        }
        return e;
    }
