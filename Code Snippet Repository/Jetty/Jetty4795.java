    @Override
    public ListIterator<E> listIterator(int index)
    {

        _tailLock.lock();
        try
        {

            _headLock.lock();
            try
            {
                Object[] elements = new Object[size()];
                if (size() > 0)
                {
                    int head = _indexes[HEAD_OFFSET];
                    int tail = _indexes[TAIL_OFFSET];
                    if (head < tail)
                    {
                        System.arraycopy(_elements,head,elements,0,tail - head);
                    }
                    else
                    {
                        int chunk = _elements.length - head;
                        System.arraycopy(_elements,head,elements,0,chunk);
                        System.arraycopy(_elements,0,elements,chunk,tail);
                    }
                }
                return new Itr(elements,index);
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
