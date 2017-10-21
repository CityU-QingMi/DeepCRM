    @SuppressWarnings("")
    @Override
    public E poll(long time, TimeUnit unit) throws InterruptedException
    {
        long nanos = unit.toNanos(time);
        E e = null;

        _headLock.lockInterruptibly(); // Size cannot shrink
        try
        {
            try
            {
                while (_size.get() == 0)
                {
                    if (nanos <= 0)
                        return null;
                    nanos = _notEmpty.awaitNanos(nanos);
                }
            }
            catch (InterruptedException x)
            {
                _notEmpty.signal();
                throw x;
            }

            int head = _indexes[HEAD_OFFSET];
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
