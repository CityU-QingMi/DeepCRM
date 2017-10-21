    @Override
    public void onWebSocketBinary(byte[] payload, int offset, int len)
    {
        if (isNotConnected())
        {
            return;
        }
        try
        {
            ByteBuffer buf = ByteBuffer.wrap(payload,offset,len);
            getRemote().sendBytes(buf);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
