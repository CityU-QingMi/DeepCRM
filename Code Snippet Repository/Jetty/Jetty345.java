    public void setMaxMultiplex(int maxMultiplex)
    {
        lock();
        try
        {
            this.maxMultiplex = maxMultiplex;
        }
        finally
        {
            unlock();
        }
    }
