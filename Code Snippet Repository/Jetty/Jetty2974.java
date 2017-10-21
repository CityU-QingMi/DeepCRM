    @Override
    public void close()
    {
        try
        {
            synchronized (lock)
            {
                out.close();
                _isClosed = true;
            }
        }
        catch (IOException ex)
        {
            setError(ex);
        }
    }
