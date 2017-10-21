    @OnWebSocketMessage
    public void onText(String message) throws IOException
    {
        LOG.debug("onText({})",message);

        // echo the message back.
        RemoteEndpoint remote = session.getRemote();
        remote.sendString(message, null);
        if (remote.getBatchMode() == BatchMode.ON)
            remote.flush();
    }
