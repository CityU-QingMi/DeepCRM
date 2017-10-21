    @Override
    public void onWebSocketText(String message)
    {
        if (isConnected())
        {
            try
            {
                System.out.printf("Echoing back message [%s]%n",message);
                // echo the message back
                getRemote().sendString(message);
            }
            catch (IOException e)
            {
                e.printStackTrace(System.err);
            }
        }
    }
