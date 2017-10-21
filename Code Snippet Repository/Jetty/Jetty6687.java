    @Override
    public void onWebSocketText(String message)
    {
        if ((outbound != null) && (outbound.isOpen()))
        {
            System.out.printf("Echoing back message [%s]%n",message);
            // echo the message back
            outbound.getRemote().sendString(message,null);
        }
    }
