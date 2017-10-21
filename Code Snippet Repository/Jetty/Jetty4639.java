    @Test
    public void testGenerate_NothingEnabled() throws IOException
    {
        // Test Env
        Path homeDir = MavenTestingUtils.getTestResourcePathDir("dist-home");
        Path baseDir = testdir.getEmptyPathDir();
        String cmdLine[] = new String[] {"jetty.version=TEST"};
        
        // Configuration
        CommandLineConfigSource cmdLineSource = new CommandLineConfigSource(cmdLine);
        ConfigSources config = new ConfigSources();
        config.add(cmdLineSource);
        config.add(new JettyHomeConfigSource(homeDir));
        config.add(new JettyBaseConfigSource(baseDir));
        
        // Initialize
        BaseHome basehome = new BaseHome(config);
        
        StartArgs args = new StartArgs(basehome);
        args.parse(config);

        Modules modules = new Modules(basehome, args);
        modules.registerAll();

        Path outputFile = basehome.getBasePath("graph.dot");

        ModuleGraphWriter writer = new ModuleGraphWriter();
        writer.write(modules,outputFile);

        Assert.assertThat("Output File Exists",FS.exists(outputFile),is(true));
    }
