    @OnMessage
    public void onMessage(Integer i) throws IOException
    {
        if (i == null)
        {
            session.getAsyncRemote().sendText("Error: Integer is null");
        }
        else
        {
            String msg = i.toString();
            session.getAsyncRemote().sendText(msg);
        }
    }
