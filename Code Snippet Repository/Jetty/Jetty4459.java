    public BaseHome(CommandLineConfigSource cmdLineSource) throws IOException
    {
        sources = new ConfigSources();
        sources.add(cmdLineSource);
        this.homeDir = cmdLineSource.getHomePath();
        this.baseDir = cmdLineSource.getBasePath();

        // TODO this is cyclic construction as start log uses BaseHome, but BaseHome constructor
        // calls other constructors that log.   This appears to be a workable sequence.
        StartLog.getInstance().initialize(this,cmdLineSource);
        
        sources.add(new JettyBaseConfigSource(cmdLineSource.getBasePath()));
        sources.add(new JettyHomeConfigSource(cmdLineSource.getHomePath()));

        System.setProperty(JETTY_HOME,homeDir.toAbsolutePath().toString());
        System.setProperty(JETTY_BASE,baseDir.toAbsolutePath().toString());
    }
