    public static void main( String[] args ) throws Exception
    {
        Server server = new Server(8080);

        ServletContextHandler context = new ServletContextHandler(
                ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        // Add the echo socket servlet to the /echo path map
        context.addServlet(new ServletHolder(EchoServlet.class), "/echo");

        server.start();
        context.dumpStdErr();
        server.join();
    }
