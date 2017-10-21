    @Override
    public void write(char buf[], int off, int len)
    {
        try
        {
            synchronized (lock)
            {
                isOpen();
                out.write(buf,off,len);
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
