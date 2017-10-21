    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        if (configuration.getFactory().isUpgradeRequest(request,response))
        {
            MappedResource<WebSocketCreator> resource = configuration.getMatch(target);
            if (resource == null)
            {
                // no match.
                response.sendError(HttpServletResponse.SC_NOT_FOUND,"No websocket endpoint matching path: " + target);
                return;
            }

            WebSocketCreator creator = resource.getResource();

            // Store PathSpec resource mapping as request attribute
            request.setAttribute(PathSpec.class.getName(),resource);

            // We have an upgrade request
            if (configuration.getFactory().acceptWebSocket(creator,request,response))
            {
                // We have a socket instance created
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
