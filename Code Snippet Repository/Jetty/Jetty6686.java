    @OnWebSocketMessage
    public void onText(Session session, String message)
    {
        if (session.isOpen())
        {
            System.out.printf("Echoing back message [%s]%n",message);
            // echo the message back
            session.getRemote().sendString(message,null);
        }
    }
