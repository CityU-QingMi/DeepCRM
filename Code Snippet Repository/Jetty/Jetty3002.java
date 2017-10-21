    @Override
    public void onOpened(Connection connection)
    {
        EndPoint endp = connection.getEndPoint();
        boolean ssl=false;
        
        if (_ssl && endp instanceof DecryptedEndPoint)
        {
            endp = ((DecryptedEndPoint)endp).getSslConnection().getEndPoint();
            ssl=true;
        }
        
        if (endp instanceof SocketChannelEndPoint) 
        {
            Socket socket = ((SocketChannelEndPoint)endp).getSocket();
            customize(socket,connection.getClass(),ssl);
        }
    }
