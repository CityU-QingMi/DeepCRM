    public ByteBuffer takeOutput()
    {
        ByteBuffer b;

        try(Locker.Lock lock = _locker.lock())
        {
            b=_out;
            _out=BufferUtil.allocate(b.capacity());
        }
        getWriteFlusher().completeWrite();
        return b;
    }
