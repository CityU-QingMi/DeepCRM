    @Override
    public void close()
    {
        if (autoClose)
        {
            try
            {
                stream.close();
            }
            catch (IOException x)
            {
                LOG.ignore(x);
            }
        }
    }
