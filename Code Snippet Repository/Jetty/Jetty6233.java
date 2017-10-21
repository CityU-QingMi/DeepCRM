    @OnMessage
    public void onInt(Session session, int value)
    {
        try
        {
            session.getBasicRemote().sendObject(value);
        }
        catch (IOException | EncodeException e)
        {
            e.printStackTrace();
        }
    }
