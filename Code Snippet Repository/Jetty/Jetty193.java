    @Produces
    public Session getSession(InjectionPoint injectionPoint)
    {
        if (LOG.isDebugEnabled())
        {
            LOG.debug("getSession({})",injectionPoint);
        }
        org.eclipse.jetty.websocket.api.Session sess = WebSocketScopeContext.current().getSession();
        if (sess == null)
        {
            throw new IllegalStateException("No Session Available");
        }

        if (sess instanceof javax.websocket.Session)
        {
            return (Session)sess;
        }

        throw new IllegalStateException("Incompatible Session, expected <" + javax.websocket.Session.class.getName() + ">, but got <"
                + sess.getClass().getName() + "> instead");
    }
