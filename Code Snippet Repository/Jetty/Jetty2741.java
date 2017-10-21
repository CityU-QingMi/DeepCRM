    protected void interruptAcceptors()
    {
        try (Locker.Lock lock = _locker.lock())
        {
            for (Thread thread : _acceptors)
            {
                if (thread != null)
                    thread.interrupt();
            }
        }
    }
