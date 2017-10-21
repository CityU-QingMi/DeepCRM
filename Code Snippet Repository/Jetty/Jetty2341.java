    private void close(Closeable closeable)
    {
        try
        {
            if (closeable != null)
                closeable.close();
        }
        catch (IOException x)
        {
            LOG.ignore(x);
        }
    }
