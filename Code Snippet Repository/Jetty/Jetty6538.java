    @Override
    public void openSession(WebSocketSession session)
    {
        if (LOG.isDebugEnabled())
        {
            LOG.debug("openSession({})", session);
            LOG.debug("objectFactory={}", session.getContainerScope().getObjectFactory());
        }
        this.session = session;
        this.session.getContainerScope().getObjectFactory().decorate(this.websocket);
        
        try
        {
            this.onConnect();
        }
        catch (Throwable t)
        {
            this.session.notifyError(t);
            throw t;
        }
    }
