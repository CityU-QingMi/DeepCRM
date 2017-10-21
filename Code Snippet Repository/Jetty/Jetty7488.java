    public void startJetty() throws Exception
    {
        File target = MavenTestingUtils.getTargetDir();
        File jettyBase = new File(target, "test-base");
        File webapps = new File(jettyBase, "webapps");
        File war = new File(webapps, "jmx-webapp.war");

        _server = new Server(0);

        WebAppContext context = new WebAppContext();
        context.setWar(war.getCanonicalPath());
        context.setContextPath("/jmx-webapp");
        Configuration.ClassList classlist = Configuration.ClassList
                .setServerDefault(_server);
        classlist.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration",
                "org.eclipse.jetty.annotations.AnnotationConfiguration");
        context.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",
                ".*/javax.servlet-[^/]*\\.jar$|.*/servlet-api-[^/]*\\.jar$");
        _server.setHandler(context);

        MBeanContainer mbContainer = new MBeanContainer(ManagementFactory.getPlatformMBeanServer());
        _server.addBean(mbContainer);

        ServerSocket serverSocket = new ServerSocket(0);
        int jmxPort = serverSocket.getLocalPort();
        serverSocket.close();

        _jmxURL = new JMXServiceURL("rmi", "0.0.0.0", jmxPort, "/jndi/rmi://0.0.0.0:" + jmxPort + "/jmxrmi");
        ConnectorServer jmxConnServer = new ConnectorServer(_jmxURL, "org.eclipse.jetty.jmx:name=rmiconnectorserver");
        _server.addBean(jmxConnServer);

        _server.start();
        _httpPort = ((NetworkConnector)_server.getConnectors()[0]).getLocalPort();
    }
