    public WebAppContext addWebAppContext(String warPath, String contextPath) throws Exception
    {
        WebAppContext context = new WebAppContext(_contexts, warPath, contextPath);
        SessionHandler sessionHandler = newSessionHandler();
        sessionHandler.setSessionIdManager(_sessionIdManager);
        sessionHandler.setMaxInactiveInterval(_maxInactivePeriod);   
        context.setSessionHandler(sessionHandler);

        return context;
    }
