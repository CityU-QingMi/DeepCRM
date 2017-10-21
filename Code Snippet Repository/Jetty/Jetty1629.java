    @Override
    protected void needsFillInterest() throws IOException
    {
        try(Locker.Lock lock = _locker.lock())
        {
            if (!isOpen())
                throw new ClosedChannelException();

            ByteBuffer in = _inQ.peek();
            if (BufferUtil.hasContent(in) || in==EOF)
                execute(_runFillable);
        }
    }
