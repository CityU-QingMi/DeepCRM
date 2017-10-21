    @Override
    public void println(char s[])
    {
        try
        {
            synchronized (lock)
            {
                isOpen();
                out.write(s,0,s.length);
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
