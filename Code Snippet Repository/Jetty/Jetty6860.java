    @Override
    public void close()
    {
        // WebSocketSession.close();
        super.close();

        // LifeCycle Stop
        try
        {
            stop();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
