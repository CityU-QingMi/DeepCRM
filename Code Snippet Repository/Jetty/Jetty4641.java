    @Test
    public void testLoadAllModules() throws IOException
    {
        // Test Env
        File homeDir = MavenTestingUtils.getTestResourceDir("dist-home");
        File baseDir = testdir.getEmptyPathDir().toFile();
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

        // Check versions
        assertThat("java.version.platform", args.getProperties().getString("java.version.platform"),anyOf(equalTo("8"),equalTo("9")));

        List<String> moduleNames = new ArrayList<>();
        for (Module mod : modules)
        {
            // skip alpn-boot in this test (as its behavior is jdk specific)
            if (mod.getName().equals("alpn-boot"))
            {
                continue;
            }
            moduleNames.add(mod.getName());
        }

        List<String> expected = new ArrayList<>();
        expected.add("base");
        expected.add("extra");
        expected.add("main");
        expected.add("optional");

        ConfigurationAssert.assertContainsUnordered("All Modules",expected,moduleNames);
    }
