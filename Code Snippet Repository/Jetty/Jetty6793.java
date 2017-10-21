    @OnWebSocketMessage
    public void echoBin(byte buf[], int offset, int length)
    {
        try
        {
            getRemote().sendBytes(ByteBuffer.wrap(buf,offset,length));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
