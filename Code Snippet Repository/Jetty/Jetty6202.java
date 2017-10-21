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
            session.getAsyncRemote().sendText(msg);
        }
    }
