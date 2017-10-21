    public JettyLogHandler()
    {
        if (Boolean.parseBoolean(Log.__props.getProperty("org.eclipse.jetty.util.log.DEBUG","false")))
        {
            setLevel(Level.FINEST);
        }

        if (Boolean.parseBoolean(Log.__props.getProperty("org.eclipse.jetty.util.log.IGNORED","false")))
        {
            setLevel(Level.ALL);
        }
        
        System.err.printf("%s Initialized at level [%s]%n",this.getClass().getName(),getLevel().getName());
    }
