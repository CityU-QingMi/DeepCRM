    public void listConfig(StartArgs args)
    {
        StartLog.endStartLog();
        
        // Dump Jetty Home / Base
        args.dumpEnvironment();

        // Dump JVM Args
        args.dumpJvmArgs();

        // Dump System Properties
        args.dumpSystemProperties();

        // Dump Properties
        args.dumpProperties();

        // Dump Classpath
        dumpClasspathWithVersions(args.getClasspath());

        // Dump Resolved XMLs
        args.dumpActiveXmls();
    }
