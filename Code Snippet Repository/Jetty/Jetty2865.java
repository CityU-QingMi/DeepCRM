    private void close(Closeable resource)
    {
        try
        {
            resource.close();
        }
        catch (Throwable x)
        {
            LOG.ignore(x);
        }
    }
