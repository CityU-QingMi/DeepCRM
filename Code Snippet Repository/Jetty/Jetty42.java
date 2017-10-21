    public static void main( String[] args ) throws Exception
    {
        Server server = new Server(8080);
        
        HttpConfiguration config=server.getConnectors()[0].getConnectionFactory(HttpConnectionFactory.class).getHttpConfiguration();

        RewriteCustomizer rewrite = new RewriteCustomizer();
        config.addCustomizer(rewrite);
        rewrite.addRule(new CompactPathRule());
        rewrite.addRule(new RewriteRegexRule("(.*)foo(.*)","$1FOO$2"));
        
        ServletContextHandler context = new ServletContextHandler(
                ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        context.addServlet(DumpServlet.class, "/*");

        server.start();
        server.join();
    }
