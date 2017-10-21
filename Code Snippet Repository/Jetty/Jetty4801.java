    @Override
    public boolean remove(Object o)
    {

        _tailLock.lock();
        try
        {

            _headLock.lock();
            try
            {
                if (isEmpty())
                    return false;

                final int head = _indexes[HEAD_OFFSET];
                final int tail = _indexes[TAIL_OFFSET];
                final int capacity = _elements.length;

                int i = head;
                while (true)
                {
                    if (Objects.equals(_elements[i],o))
                    {
                        remove(i >= head?i - head:capacity - head + i);
                        return true;
                    }
                    ++i;
                    if (i == capacity)
                        i = 0;
                    if (i == tail)
                        return false;
                }
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
