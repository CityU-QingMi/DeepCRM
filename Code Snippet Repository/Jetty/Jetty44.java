    public static void main( String[] args ) throws Exception
    {
        // === jetty-jmx.xml ===
        MBeanContainer mbContainer = new MBeanContainer(
                ManagementFactory.getPlatformMBeanServer());
        
        Server server = new Server(8080);
        server.addBean(mbContainer);
        
        ConnectorServer jmx = new ConnectorServer(
                new JMXServiceURL(
                        "rmi",
                        null,
                        1999,
                        "/jndi/rmi://localhost:1999/jmxrmi"),
                        "org.eclipse.jetty.jmx:name=rmiconnectorserver");
        server.addBean(jmx);
        
        server.start();
        server.dumpStdErr();
        server.join();
    }
