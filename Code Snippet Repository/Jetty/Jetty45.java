    public static void main( String[] args ) throws Exception
    {
        Server server = new Server(8080);

        ServletContextHandler context = new ServletContextHandler(
                ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        // Enable javax.websocket configuration for the context
        ServerContainer wsContainer = WebSocketServerContainerInitializer
                .configureContext(context);

        // Add your websockets to the container
        wsContainer.addEndpoint(EchoJsrSocket.class);

        server.start();
        context.dumpStdErr();
        server.join();
    }
