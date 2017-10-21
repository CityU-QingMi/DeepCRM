    @Override
    public void initialize(SessionContext context)
    {
        try
        {
            _client = _builder.build();
            _client.setEnableHeartBeat(isHeartbeats());
        }
        catch (IOException e)
        {
            throw new IllegalStateException(e);
        }
    }
