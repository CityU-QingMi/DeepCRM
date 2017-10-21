    public boolean onIdleTimeout(Throwable x)
    {
        synchronized (_inputQ)
        {
            if (_waitingForContent && !isError())
            {
                x.addSuppressed(new Throwable("HttpInput idle timeout"));
                _state = new ErrorState(x);
                return wakeup();
            }
            return false;
        }
    }
