    @SuppressWarnings("")
    @Override
    public E get(int index)
    {

        _tailLock.lock();
        try
        {

            _headLock.lock();
            try
            {
                if (index < 0 || index >= _size.get())
                    throw new IndexOutOfBoundsException("!(" + 0 + "<" + index + "<=" + _size + ")");
                int i = _indexes[HEAD_OFFSET] + index;
                int capacity = _elements.length;
                if (i >= capacity)
                    i -= capacity;
                return (E)_elements[i];
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
