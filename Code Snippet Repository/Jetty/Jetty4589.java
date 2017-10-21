    public CommandLineConfigSource(String rawargs[])
    {
        this.args = new RawArgs();
        this.props = new Props();
        for (String arg : rawargs)
        {
            this.args.addArg(arg,ORIGIN_CMD_LINE);
            this.props.addPossibleProperty(arg,ORIGIN_CMD_LINE);
        }

        // Setup ${jetty.base} and ${jetty.home}
        this.homePath = findJettyHomePath().toAbsolutePath();
        this.basePath = findJettyBasePath().toAbsolutePath();

        // Update System Properties
        setSystemProperty(BaseHome.JETTY_HOME,homePath.toAbsolutePath().toString());
        setSystemProperty(BaseHome.JETTY_BASE,basePath.toAbsolutePath().toString());
    }
