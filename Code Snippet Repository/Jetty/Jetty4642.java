    @Test
    public void testLoadShallowModulesOnly() throws IOException
    {
        // Test Env
        File homeDir = MavenTestingUtils.getTestResourceDir("jetty home with spaces");
        // intentionally setup top level resources dir (as this would have many
        // deep references)
        File baseDir = MavenTestingUtils.getTestResourcesDir();
        String cmdLine[] = new String[] { "jetty.version=TEST" };

        // Configuration
        CommandLineConfigSource cmdLineSource = new CommandLineConfigSource(cmdLine);
        ConfigSources config = new ConfigSources();
        config.add(cmdLineSource);
        config.add(new JettyHomeConfigSource(homeDir.toPath()));
        config.add(new JettyBaseConfigSource(baseDir.toPath()));

        // Initialize
        BaseHome basehome = new BaseHome(config);

        StartArgs args = new StartArgs(basehome);
        args.parse(config);

        // Test Modules
        Modules modules = new Modules(basehome,args);
        modules.registerAll();

        List<String> moduleNames = new ArrayList<>();
        for (Module mod : modules)
        {
            moduleNames.add(mod.getName());
        }

        List<String> expected = new ArrayList<>();
        expected.add("base");

        ConfigurationAssert.assertContainsUnordered("All Modules",expected,moduleNames);
    }
