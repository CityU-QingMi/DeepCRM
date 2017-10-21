    @OnMessage
    public void onMessage(Long l) throws IOException
    {
        if (l == null)
        {
            session.getAsyncRemote().sendText("Error: Long is null");
        }
        else
        {
            String msg = l.toString();
            session.getAsyncRemote().sendText(msg);
        }
    }
