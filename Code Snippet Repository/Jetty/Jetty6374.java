    @OnMessage
    public void onMessage(Boolean b) throws IOException
    {
        if (b == null)
        {
            session.getAsyncRemote().sendText("Error: Boolean is null");
        }
        else
        {
            String msg = b.toString();
            session.getAsyncRemote().sendText(msg);
        }
    }
