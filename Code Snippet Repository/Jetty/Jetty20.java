    public static void main( String[] args ) throws Exception
    {
        Server server = new Server(8080);

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] {
                new FastFileHandler(new File(System.getProperty("user.dir"))),
                new DefaultHandler() });
        server.setHandler(handlers);

        server.start();
        server.join();
    }
