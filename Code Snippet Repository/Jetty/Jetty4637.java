    @Test
    public void testWithModules() throws Exception
    {
        List<String> cmdLineArgs = new ArrayList<>();

        Path homePath = MavenTestingUtils.getTestResourceDir("dist-home").toPath().toRealPath();
        cmdLineArgs.add("jetty.home=" + homePath);
        cmdLineArgs.add("user.dir=" + homePath);
        cmdLineArgs.add("java.version=1.8.0_31");

        // Modules
        cmdLineArgs.add("--module=optional,extra");

        Main main = new Main();

        StartArgs args = main.processCommandLine(cmdLineArgs.toArray(new String[cmdLineArgs.size()]));
        BaseHome baseHome = main.getBaseHome();

        assertThat("jetty.home",baseHome.getHome(),is(homePath.toString()));
        assertThat("jetty.base",baseHome.getBase(),is(homePath.toString()));

        ConfigurationAssert.assertConfiguration(baseHome,args,"assert-home-with-module.txt");
    }
