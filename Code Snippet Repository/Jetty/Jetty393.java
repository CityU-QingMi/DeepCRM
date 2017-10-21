    @Override
    public String toString()
    {
        int size;
        lock();
        try
        {
            size = quarantine.size();
        }
        finally
        {
            unlock();
        }
        return String.format("%s[v=%d]", super.toString(), size);
    }
