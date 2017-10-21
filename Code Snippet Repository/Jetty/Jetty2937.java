    private void extractMultipartParameters(MultiMap<String> result)
    {
        try
        {
            getParts(result);
        }
        catch (IOException | ServletException e)
        {
            LOG.debug(e);
            throw new RuntimeIOException(e);
        }
    }
