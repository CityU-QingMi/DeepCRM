    @OnMessage
    public void onMessage(Double d) throws IOException
    {
        if (d == null)
        {
            session.getAsyncRemote().sendText("Error: Double is null");
        }
        else
        {
            String msg = String.format(Locale.US, "%.4f",d);
            session.getAsyncRemote().sendText(msg);
        }
    }
