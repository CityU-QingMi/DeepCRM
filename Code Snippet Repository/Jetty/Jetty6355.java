    private ServerContainer setupServer(int port) throws DeploymentException, ServletException, URISyntaxException, MalformedURLException, IOException
    {
        server = new Server();
        
        server.setDumpAfterStart(true);
        
        HttpConfiguration httpConf = new HttpConfiguration();
        httpConf.setSendServerVersion(true);
        
        ServerConnector connector = new ServerConnector(server, new HttpConnectionFactory(httpConf));
        connector.setPort(port);
        server.addConnector(connector);

        String resourcePath = "/jsr-browser-debug-tool/index.html";
        URL urlStatics = JsrBrowserDebugTool.class.getResource(resourcePath);
        Objects.requireNonNull(urlStatics,"Unable to find " + resourcePath + " in classpath");
        String urlBase = urlStatics.toURI().toASCIIString().replaceFirst("/[^/]*$","/");

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        context.setBaseResource(Resource.newResource(urlBase));

        ServletHolder holder = context.addServlet(DefaultServlet.class,"/");
        holder.setInitParameter("dirAllowed","true");
        server.setHandler(context);

        ServerContainer container = WebSocketServerContainerInitializer.configureContext(context);
        container.addEndpoint(JsrBrowserSocket.class);

        LOG.info("{} setup on port {}",this.getClass().getName(),port);
        return container;
    }
