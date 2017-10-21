    @Override
    public HttpContext createContext(String path, HttpHandler httpHandler)
    {
        checkIfContextIsFree(path);

        JettyHttpContext context = new JettyHttpContext(this, path, httpHandler);
        HttpSpiContextHandler jettyContextHandler = context.getJettyContextHandler();

        ContextHandlerCollection chc = findContextHandlerCollection(_server.getHandlers());
        if (chc == null)
            throw new RuntimeException("could not find ContextHandlerCollection, you must configure one");

        chc.addHandler(jettyContextHandler);
        _contexts.put(path, context);

        return context;
    }
