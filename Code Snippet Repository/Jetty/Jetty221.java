    @OnMessage
    public void onMessage(String message)
    {
        LOG.info("onMessage({0})",quoted(message));
        
        try
        {
            RemoteEndpoint.Basic remote = wsSession.getBasicRemote();
            LOG.info("Remote.Basic: {0}", remote);
            
            if ("info".equalsIgnoreCase(message))
            {
                LOG.info("returning 'info' details");
                remote.sendText("HttpSession = " + httpSession);
            }
            else if ("close".equalsIgnoreCase(message))
            {
                LOG.info("closing session");
                wsSession.close();
            }
            else
            {
                LOG.info("echoing message as-is");
                remote.sendText(message);
            }
        }
        catch (Throwable t)
        {
            LOG.warn(t);
        }
    }
