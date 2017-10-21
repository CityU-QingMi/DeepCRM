    @OnWebSocketMessage
    public void onInputStream(InputStream stream)
    {
        if (LOG.isDebugEnabled())
            LOG.debug("{} onInputStream({})",id,stream);
        try
        {
            String msg = IO.toString(stream);
            messageQueue.add(msg);
        }
        catch (IOException e)
        {
            errorQueue.add(e);
        }
    }
