    @Before
    public void setupBaseHome() throws IOException
    {
        Path homeDir = testdir.getEmptyPathDir();
        
        ConfigSources config = new ConfigSources();
        config.add(new JettyHomeConfigSource(homeDir));
        config.add(new JettyBaseConfigSource(homeDir));

        this.baseHome = new BaseHome(config);
    }
