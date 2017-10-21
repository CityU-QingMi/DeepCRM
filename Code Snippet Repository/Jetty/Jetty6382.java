    @OnMessage
    public void onMessage(Short s) throws IOException
    {
        if (s == null)
        {
            session.getAsyncRemote().sendText("Error: Short is null");
        }
        else
        {
            String msg = s.toString();
            session.getAsyncRemote().sendText(msg);
        }
    }
