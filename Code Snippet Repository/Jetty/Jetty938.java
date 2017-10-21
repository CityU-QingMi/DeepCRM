    @Override
    public void close()
    {
        try
        {
            _resp.getOutputStream().close();
        }
        catch (IOException ex)
        {
            throw new RuntimeException(ex);
        }
    }
