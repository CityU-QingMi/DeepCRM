    @OnWebSocketMessage
    public void onText(String message) throws IOException
    {
        LOG.debug("onText({})",message);
        // Test the RFC 6455 close code 1011 that should close
        // trigger a WebSocket server terminated close.
        if (message.equals("CRASH"))
        {
            throw new RuntimeException("Something bad happened");
        }

        // echo the message back.
        RemoteEndpoint remote = session.getRemote();
        remote.sendString(message, null);
        if (remote.getBatchMode() == BatchMode.ON)
            remote.flush();
    }
