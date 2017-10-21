    @Override
    public void write(String s, int off, int len)
    {
        try
        {
            synchronized (lock)
            {
                isOpen();
                out.write(s,off,len);
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
