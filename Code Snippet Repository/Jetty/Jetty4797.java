    @SuppressWarnings("")
    @Override
    public E poll()
    {
        if (_size.get() == 0)
            return null;

        E e = null;

        _headLock.lock(); // Size cannot shrink
        try
        {
            if (_size.get() > 0)
            {
                final int head = _indexes[HEAD_OFFSET];
                e = (E)_elements[head];
                _elements[head] = null;
                _indexes[HEAD_OFFSET] = (head + 1) % _elements.length;
                if (_size.decrementAndGet() > 0)
                    _notEmpty.signal();
            }
        }
        finally
        {
            _headLock.unlock();
        }
        return e;
    }
