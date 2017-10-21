    @OnMessage
    public void onMessage(Byte b) throws IOException
    {
        if (b == null)
        {
            session.getAsyncRemote().sendText("Error: Byte is null");
        }
        else
        {
            String msg = String.format("0x%02X",b);
            session.getAsyncRemote().sendText(msg);
        }
    }
