    @OnMessage
    public void onMessage(Float f) throws IOException
    {
        if (f == null)
        {
            session.getAsyncRemote().sendText("Error: Float is null");
        }
        else
        {
            String msg = String.format(Locale.US, "%.4f",f);
            session.getAsyncRemote().sendText(msg);
        }
    }
