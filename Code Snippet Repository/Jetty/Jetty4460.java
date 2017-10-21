    public BaseHome(ConfigSources sources)
    {
        this.sources = sources;
        Path home = null;
        Path base = null;
        for (ConfigSource source : sources)
        {
            if (source instanceof CommandLineConfigSource)
            {
                CommandLineConfigSource cmdline = (CommandLineConfigSource)source;
                home = cmdline.getHomePath();
                base = cmdline.getBasePath();
            }
            else if (source instanceof JettyBaseConfigSource)
            {
                base = ((JettyBaseConfigSource)source).getDir();
            }
            else if (source instanceof JettyHomeConfigSource)
            {
                home = ((JettyHomeConfigSource)source).getDir();
            }
        }

        Objects.requireNonNull(home,"jetty.home cannot be null");
        this.homeDir = home;
        this.baseDir = (base != null)?base:home;

        System.setProperty(JETTY_HOME,homeDir.toAbsolutePath().toString());
        System.setProperty(JETTY_BASE,baseDir.toAbsolutePath().toString());
    }
