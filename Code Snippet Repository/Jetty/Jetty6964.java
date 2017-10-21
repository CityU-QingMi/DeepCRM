    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        if (webSocketFactory.isUpgradeRequest(request,response))
        {
            // We have an upgrade request
            if (webSocketFactory.acceptWebSocket(request,response))
            {
                // We have a socket instance created
                baseRequest.setHandled(true);
                return;
            }
            // If we reach this point, it means we had an incoming request to upgrade
            // but it was either not a proper websocket upgrade, or it was possibly rejected
            // due to incoming request constraints (controlled by WebSocketCreator)
            if (response.isCommitted())
            {
                // not much we can do at this point.
                return;
            }
        }
        super.handle(target,baseRequest,request,response);
    }
