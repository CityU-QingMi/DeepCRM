    @SuppressWarnings("")
    @Override
    public E set(int index, E e)
    {
        Objects.requireNonNull(e);

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
                E old = (E)_elements[i];
                _elements[i] = e;
                return old;
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
