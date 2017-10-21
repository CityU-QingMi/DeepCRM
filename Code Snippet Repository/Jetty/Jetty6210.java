    public void writeMessage(String message)
    {
        System.out.printf("Writing: \"%s\"%n",message);
        try
        {
            session.getBasicRemote().sendText(message);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
