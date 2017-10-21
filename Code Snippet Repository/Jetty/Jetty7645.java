    @Override
    public Object createWebSocket(ServletUpgradeRequest req, ServletUpgradeResponse resp)
    {
        if (req.hasSubProtocol("chat"))
        {
            resp.setAcceptedSubProtocol("chat");
            return new ChatWebSocket();
        }
        return null;
    }
