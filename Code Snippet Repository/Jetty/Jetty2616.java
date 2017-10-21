    public void usage(String error)
    {
        if (error!=null)
            System.err.println("ERROR: "+error);
        System.err.println("Usage: java [-Djetty.home=dir] -jar jetty-runner.jar [--help|--version] [ server opts] [[ context opts] context ...] ");
        System.err.println("Server opts:");
        System.err.println(" --version                           - display version and exit");
        System.err.println(" --log file                          - request log filename (with optional 'yyyy_mm_dd' wildcard");
        System.err.println(" --out file                          - info/warn/debug log filename (with optional 'yyyy_mm_dd' wildcard");
        System.err.println(" --host name|ip                      - interface to listen on (default is all interfaces)");
        System.err.println(" --port n                            - port to listen on (default 8080)");
        System.err.println(" --stop-port n                       - port to listen for stop command");
        System.err.println(" --stop-key n                        - security string for stop command (required if --stop-port is present)");
        System.err.println(" [--jar file]*n                      - each tuple specifies an extra jar to be added to the classloader");
        System.err.println(" [--lib dir]*n                       - each tuple specifies an extra directory of jars to be added to the classloader");
        System.err.println(" [--classes dir]*n                   - each tuple specifies an extra directory of classes to be added to the classloader");
        System.err.println(" --stats [unsecure|realm.properties] - enable stats gathering servlet context");
        System.err.println(" [--config file]*n                   - each tuple specifies the name of a jetty xml config file to apply (in the order defined)");
        System.err.println("Context opts:");
        System.err.println(" [[--path /path] context]*n          - WAR file, web app dir or context xml file, optionally with a context path");
        System.exit(1);
    }
