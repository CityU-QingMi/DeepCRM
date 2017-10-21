    public void start(HandlerWrapper wrapper) throws Exception
    {
        server = new Server();
        connector = new ServerConnector(server);
        server.addConnector(connector);
        Handler shutdown = new ShutdownHandler(shutdownToken);
        Handler handler = shutdown;
        if (wrapper != null)
        {
            wrapper.setHandler(shutdown);
            handler = wrapper;
        }
        server.setHandler(handler);
        server.start();
    }
