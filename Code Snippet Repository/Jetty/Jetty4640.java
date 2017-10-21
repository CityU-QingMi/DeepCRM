    @Test
    public void testLoadMain() throws IOException
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
        
        File file = MavenTestingUtils.getTestResourceFile("dist-home/modules/main.mod");
        Module module = new Module(basehome,file.toPath());
        
        Assert.assertThat("Module Name",module.getName(),is("main"));
        Assert.assertThat("Module Depends Size",module.getDepends().size(),is(1));
        Assert.assertThat("Module Depends",module.getDepends(),containsInAnyOrder("base"));
        Assert.assertThat("Module Xmls Size",module.getXmls().size(),is(1));
        Assert.assertThat("Module Lib Size",module.getLibs().size(),is(2));
        Assert.assertThat("Module Lib",module.getLibs(),contains("lib/main.jar","lib/other.jar"));
    }
