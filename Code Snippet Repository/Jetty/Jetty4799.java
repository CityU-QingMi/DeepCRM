    @SuppressWarnings("")
    @Override
    public E take() throws InterruptedException
    {
        E e = null;

        _headLock.lockInterruptibly(); // Size cannot shrink
        try
        {
            try
            {
                while (_size.get() == 0)
                {
                    _notEmpty.await();
                }
            }
            catch (InterruptedException ie)
            {
                _notEmpty.signal();
                throw ie;
            }

            final int head = _indexes[HEAD_OFFSET];
            e = (E)_elements[head];
            _elements[head] = null;
            _indexes[HEAD_OFFSET] = (head + 1) % _elements.length;

            if (_size.decrementAndGet() > 0)
                _notEmpty.signal();
        }
        finally
        {
            _headLock.unlock();
        }
        return e;
    }
