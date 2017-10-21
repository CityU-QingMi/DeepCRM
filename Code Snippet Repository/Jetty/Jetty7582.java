    public ServletContextHandler addContext(String contextPath) throws Exception
    {
        ServletContextHandler context = new ServletContextHandler(_contexts, contextPath);
        SessionHandler sessionHandler = newSessionHandler();
        sessionHandler.setSessionIdManager(_sessionIdManager);
        sessionHandler.setMaxInactiveInterval(_maxInactivePeriod);
        context.setSessionHandler(sessionHandler);

        return context;
    }
