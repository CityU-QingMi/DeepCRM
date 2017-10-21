    @Override
    public void println()
    {
        try
        {
            synchronized (lock)
            {
                isOpen();
                out.write(__lineSeparator);
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
