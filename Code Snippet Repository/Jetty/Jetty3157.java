    public Set<SessionHandler> getSessionHandlers()
    {
        Set<SessionHandler> handlers = new HashSet<>();
        Handler[] tmp = _server.getChildHandlersByClass(SessionHandler.class);
        if (tmp != null)
        {
            for (Handler h:tmp)
                handlers.add((SessionHandler)h);
        }
        return handlers;
    }
