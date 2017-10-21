    @OnWebSocketMessage
    public void onBinary(Session session, byte buf[], int offset, int length) throws IOException
    {
        if (!session.isOpen())
        {
            LOG.warn("Session is closed");
            return;
        }
        RemoteEndpoint remote = session.getRemote();
        remote.sendBytes(ByteBuffer.wrap(buf, offset, length), null);
        if (remote.getBatchMode() == BatchMode.ON)
            remote.flush();
    }
