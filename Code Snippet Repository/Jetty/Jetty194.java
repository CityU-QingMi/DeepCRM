    @Produces
    public Session getSession(InjectionPoint injectionPoint)
    {
        if (LOG.isDebugEnabled())
        {
            LOG.debug("getSession({})",injectionPoint);
        }
        WebSocketScopeContext ctx = WebSocketScopeContext.current();
        if (ctx == null)
        {
            throw new IllegalStateException("Not in a " + WebSocketScope.class.getName());
        }
        org.eclipse.jetty.websocket.api.Session sess = ctx.getSession();
        if (sess == null)
        {
            throw new IllegalStateException("No Session Available");
        }
        return sess;
    }
