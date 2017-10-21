    @Override
    public void customize(Connector connector, HttpConfiguration channelConfig, Request request)
    {
        try
        {
            matchAndApply(request.getPathInfo(), request, request.getResponse());
        }
        catch (IOException e)
        {
            throw new RuntimeIOException(e);
        }
    }
