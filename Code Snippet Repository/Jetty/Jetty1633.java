    public ByteBuffer waitForOutput(long time,TimeUnit unit) throws InterruptedException
    {
        ByteBuffer b;

        try(Locker.Lock lock = _locker.lock())
        {
            while (BufferUtil.isEmpty(_out) && !isOutputShutdown())
            {
                _hasOutput.await(time,unit);
            }
            b=_out;
            _out=BufferUtil.allocate(b.capacity());
        }
        getWriteFlusher().completeWrite();
        return b;
    }
