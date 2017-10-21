    public int getMaxMultiplex()
    {
        lock();
        try
        {
            return maxMultiplex;
        }
        finally
        {
            unlock();
        }
    }
