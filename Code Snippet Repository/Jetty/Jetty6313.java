    public boolean getClosed()
    {
        remoteLock.lock();
        try
        {
            return (remote == null);
        }
        finally
        {
            remoteLock.unlock();
        }
    }
