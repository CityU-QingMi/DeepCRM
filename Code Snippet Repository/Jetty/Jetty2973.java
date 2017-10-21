    @Override
    public void flush()
    {
        try
        {
            synchronized (lock)
            {
                isOpen();
                out.flush();
            }
        }
        catch (IOException ex)
        {
            setError(ex);
        }
    }
