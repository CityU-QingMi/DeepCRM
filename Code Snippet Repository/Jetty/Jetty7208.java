    @Override
    public Object createWebSocket(ServletUpgradeRequest req, ServletUpgradeResponse resp)
    {
        try
        {
            // Is Authenticated?
            Principal principal = req.getUserPrincipal();
            if (principal == null)
            {
                resp.sendForbidden("Not authenticated yet");
                return null;
            }

            // Is Authorized?
            if (!req.isUserInRole("websocket"))
            {
                resp.sendForbidden("Not authenticated yet");
                return null;
            }

            // Return websocket
            return new MyEchoSocket();
        }
        catch (IOException e)
        {
            e.printStackTrace(System.err);
        }
        // no websocket
        return null;
    }
