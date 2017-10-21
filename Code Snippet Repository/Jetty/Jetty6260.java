    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response)
    {
        // Is Authenticated?
        Principal principal = request.getUserPrincipal();
        if (principal == null)
        { 
            throw new RuntimeException("Not authenticated");
        }

        // Is Authorized?
        if (!request.isUserInRole("websocket"))
        {
            throw new RuntimeException("Not authorized");
        }
        
        // normal operation
        super.modifyHandshake(sec,request,response);
    }
