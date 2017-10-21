    @OnMessage
    public void onMessage(Character c) throws IOException
    {
        if (c == null)
        {
            session.getAsyncRemote().sendText("Error: Character is null");
        }
        else
        {
            String msg = c.toString();
            session.getAsyncRemote().sendText(msg);
        }
    }
