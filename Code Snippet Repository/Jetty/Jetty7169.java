    @OnWebSocketMessage
    public void onText(Session session, String message) throws IOException
    {
        if (!session.isOpen())
        {
            LOG.warn("Session is closed");
            return;
        }
        RemoteEndpoint remote = session.getRemote();
        remote.sendString(message, null);
        if (remote.getBatchMode() == BatchMode.ON)
            remote.flush();
    }
