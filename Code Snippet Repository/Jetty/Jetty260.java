    @Override
    public void close()
    {
        try
        {
            if (iterator instanceof Closeable)
                ((Closeable)iterator).close();
        }
        catch (Throwable x)
        {
            LOG.ignore(x);
        }
    }
