    @Override
    public void write(int c)
    {
        try
        {
            synchronized (lock)
            {
                isOpen();
                out.write(c);
            }
        }
        catch (InterruptedIOException ex)
        {
            LOG.debug(ex);
            Thread.currentThread().interrupt();
        }
        catch (IOException ex)
        {
            setError(ex);
        }
    }
