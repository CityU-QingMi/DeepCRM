    @OnMessage
    public void onMessage(Session session, Reader reader)
    {
        try (Writer writer = session.getBasicRemote().getSendWriter())
        {
            IO.copy(reader,writer);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
