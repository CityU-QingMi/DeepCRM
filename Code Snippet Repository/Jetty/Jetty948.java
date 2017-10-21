    @Override
    public HttpServer createHttpServer(InetSocketAddress addr, int backlog)
            throws IOException
    {
        Server server = _server;
        boolean shared = true;

        if (server == null)
        {
            ThreadPool threadPool = new DelegatingThreadPool(new QueuedThreadPool());
            server = new Server(threadPool);

            HandlerCollection handlerCollection = new HandlerCollection();
            handlerCollection.setHandlers(new Handler[] {new ContextHandlerCollection(), new DefaultHandler()});
            server.setHandler(handlerCollection);

            shared = false;
        }

        JettyHttpServer jettyHttpServer = new JettyHttpServer(server, shared);
        jettyHttpServer.bind(addr, backlog);
        return jettyHttpServer;
    }
