    public static void close(Closeable closeable)
    {
        try
        {
            if (closeable != null)
                closeable.close();
        }
        catch (IOException ignore)
        {
            LOG.ignore(ignore);
        }
    }
