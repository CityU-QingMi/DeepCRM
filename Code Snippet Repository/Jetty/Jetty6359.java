    @OnMessage
    public void onMessage(Date d) throws IOException
    {
        if (d == null)
        {
            session.getAsyncRemote().sendText("Error: Date is null");
        }
        else
        {
            String msg = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT).format(d);
            // The encoder declared in the @ServerEndpoint will be used
            session.getAsyncRemote().sendText(msg);
        }
    }
