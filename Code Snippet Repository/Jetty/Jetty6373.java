    @OnMessage
    public void onMessage(Boolean b, @PathParam("a") Boolean param) throws IOException
    {
        if (b == null)
        {
            session.getAsyncRemote().sendText("Error: Boolean is null");
        }
        else
        {
            String msg = String.format("%b|%b", b, param);
            session.getAsyncRemote().sendText(msg);
        }
    }
