    public void reset()
    {
        try(Locker.Lock lock = _locker.lock())
        {
            _inQ.clear();
            _hasOutput.signalAll();
            BufferUtil.clear(_out);
        }
        super.reset();
    }
