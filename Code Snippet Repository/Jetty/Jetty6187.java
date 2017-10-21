    @Override
    public void onWebSocketBinary(byte[] payload, int offset, int len)
    {
        if (isNotConnected())
            return;

        try
        {
            RemoteEndpoint remote = getRemote();
            remote.sendBytes(BufferUtil.toBuffer(payload, offset, len), null);
            if (remote.getBatchMode() == BatchMode.ON)
                remote.flush();
        }
        catch (IOException x)
        {
            throw new RuntimeIOException(x);
        }
    }
