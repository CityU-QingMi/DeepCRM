    @OnMessage
    public void onMessage(Integer i, @PathParam("a") int param) throws IOException
    {
        if (i == null)
        {
            session.getAsyncRemote().sendText("Error: Integer is null");
        }
        else
        {
            String msg = String.format("%d|%d",i,param);
            session.getAsyncRemote().sendText(msg);
        }
    }
